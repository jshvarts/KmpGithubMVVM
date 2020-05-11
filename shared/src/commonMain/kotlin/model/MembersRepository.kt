package com.jshvarts.kmp.shared.model

import com.jshvarts.kmp.shared.ApplicationDispatcher
import com.jshvarts.kmp.shared.api.GithubApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MembersRepository(private val api: GithubApi) {
  fun fetchMembersAsFlow(): Flow<List<Member>> {
    return flow {
      emit(api.getMembers())
    }.flowOn(ApplicationDispatcher)
  }

  /**
   * To be used by iOS
   */
  fun fetchMembers(success: (List<Member>) -> Unit) {
    GlobalScope.launch(ApplicationDispatcher) {
      fetchMembersAsFlow()
        .collect {
          success(it)
        }
    }
  }
}
