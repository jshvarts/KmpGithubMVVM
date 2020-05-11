package com.jshvarts.kmp.shared.model

import com.jshvarts.kmp.db.KmpGithubDatabase
import com.jshvarts.kmp.shared.ApplicationDispatcher
import com.jshvarts.kmp.shared.api.DataLoadException
import com.jshvarts.kmp.shared.api.GithubApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

expect fun createDb(): KmpGithubDatabase

@ExperimentalCoroutinesApi
class MembersRepository(private val api: GithubApi) {
  private val membersLocalDb = createDb()
  private val membersLocalDbQueries = membersLocalDb.kmpGithubQueries

  /**
   * If [force] is set to true, attempt to load data from remote api.
   * If remote api is not available. throw [DataLoadException]
   *
   * If [force] is set to false, attempt to load data from local cache.
   * If local cache is not available, throw [DataLoadException]
   */
  fun fetchMembersAsFlow(force: Boolean): Flow<List<Member>> {
    return if (force) getMembersFromRemote() else getMembersFromCache()
  }

  /**
   * To be used by iOS
   */
  fun fetchMembers(success: (List<Member>) -> Unit) {
    GlobalScope.launch(ApplicationDispatcher) {
      fetchMembersAsFlow(force = true)
          .collect {
            success(it)
          }
    }
  }

  private fun cacheMembers(members: List<Member>) {
    membersLocalDbQueries.deleteAll()
    members.forEach { member ->
      membersLocalDbQueries.insertItem(
          member.id,
          member.login,
          member.avatarUrl
      )
    }
  }

  private fun getMembersFromRemote(): Flow<List<Member>> {
    println("Getting members from remote")

    return flow {
      val members = api.getMembers()
      cacheMembers(members)
      emit(api.getMembers())
    }
        .catch { error(DataLoadException()) }
        .flowOn(ApplicationDispatcher)
  }

  private fun getMembersFromCache(): Flow<List<Member>> {
    println("Getting members from cache")

    fun loadMembers() = membersLocalDbQueries.selectAll()
        .executeAsList()
        .map { Member(id = it.id, login = it.login, avatarUrl = it.avatarUrl) }

    return flow { emit(loadMembers()) }
        .catch { error(DataLoadException()) }
        .flowOn(ApplicationDispatcher)
  }
}
