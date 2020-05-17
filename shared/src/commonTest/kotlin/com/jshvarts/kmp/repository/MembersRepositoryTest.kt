package com.jshvarts.kmp.repository

import com.jshvarts.kmp.AndroidJUnit4
import com.jshvarts.kmp.BaseDatabaseTest
import com.jshvarts.kmp.RunWith
import com.jshvarts.kmp.api.GithubApi
import kotlin.test.Test

class MembersRepositoryTest : BaseDatabaseTest() {
  private val queries
    get() = database.kmpGithubQueries

  @Test
  fun `fetch members success`() {
    val selectAll= queries.selectAll()

    val repository = MembersRepository(
        api = GithubApi(),
        queries = queries
    )
    //repository.fetchMembersAsFlow(true)
  }
}
