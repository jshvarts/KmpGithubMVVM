package com.jshvarts.kmp.shared.repository

import com.jshvarts.kmp.db.KmpGithubDatabase
import com.jshvarts.kmp.db.KmpGithubQueries
import com.jshvarts.kmp.shared.api.DataLoadException
import com.jshvarts.kmp.shared.api.GithubApi
import com.jshvarts.kmp.shared.applicationDispatcher
import com.jshvarts.kmp.shared.model.Member
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

internal expect fun cache(): KmpGithubDatabase

@ExperimentalCoroutinesApi
class MembersRepository(
  private val api: GithubApi,
  private val cache: KmpGithubDatabase,
  private val queries: KmpGithubQueries = cache.kmpGithubQueries
) {
  constructor() : this(api = GithubApi(), cache = cache())

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
   * Used by iOS
   */
  fun fetchMembers(success: (List<Member>) -> Unit) {
    GlobalScope.launch(applicationDispatcher) {
      fetchMembersAsFlow(force = true)
          .collect {
            success(it)
          }
    }
  }

  private fun cacheMembers(members: List<Member>) {
    queries.deleteAll()
    members.forEach { member ->
      queries.insertItem(
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
        .flowOn(applicationDispatcher)
  }

  private fun getMembersFromCache(): Flow<List<Member>> {
    println("Getting members from cache")

    fun loadMembers() = queries.selectAll()
        .executeAsList()
        .map { Member(id = it.id, login = it.login, avatarUrl = it.avatarUrl) }

    return flow { emit(loadMembers()) }
        .catch { error(DataLoadException()) }
        .flowOn(applicationDispatcher)
  }
}