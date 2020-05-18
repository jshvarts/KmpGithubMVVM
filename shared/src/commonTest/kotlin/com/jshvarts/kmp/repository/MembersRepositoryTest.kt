package com.jshvarts.kmp.repository

import com.jshvarts.kmp.AndroidJUnit4
import com.jshvarts.kmp.BaseDatabaseTest
import com.jshvarts.kmp.RunWith
import com.jshvarts.kmp.api.GithubApi
import com.jshvarts.kmp.model.Member
import com.jshvarts.kmp.runTest
import kotlinx.coroutines.flow.collect
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

private val EXPECTED_MEMBER_LIST = listOf(
    Member(
        id = 1L,
        login = "someuser",
        avatarUrl = "http://www.example.com/avatar.png"
    )
)

@RunWith(AndroidJUnit4::class)
class MembersRepositoryTest : BaseDatabaseTest() {

  private val memberQueries get() = database.kmpGithubQueries

  @Test
  fun `when fetch members as flow and force and api succeeds, then gets members from remote and caches data`() = runTest {
    memberQueries.deleteAll()

    val githubApi = GithubApiMock()

    val repository = MembersRepository(
        api = githubApi,
        queries = memberQueries
    )

    val actualMembers = repository.fetchMembersAsFlow(true)

    actualMembers.collect {
      assertEquals(it, EXPECTED_MEMBER_LIST)

      val savedMembers = database.kmpGithubQueries.selectAll()
          .executeAsList()
          .map { entity ->
            Member(
                id = entity.id,
                login = entity.login,
                avatarUrl = entity.avatarUrl
            )
          }
      assertEquals(EXPECTED_MEMBER_LIST, savedMembers)
    }
  }

  @Test
  fun `when fetch members as flow and force and api fails, then emits correct exception`() = runTest {
    val githubApi = GithubApiMock().apply {
      throwOnRequest = true
    }

    val repository = MembersRepository(
        api = githubApi,
        queries = memberQueries
    )

    val actualMembers = repository.fetchMembersAsFlow(true)
    assertFailsWith<IllegalStateException> { actualMembers.collect() }
  }
}

class GithubApiMock : GithubApi {
  var jsonRequested = false
  var throwOnRequest = false

  override suspend fun getMembers(): List<Member> {
    if (throwOnRequest) throw Exception()
    jsonRequested = true
    return EXPECTED_MEMBER_LIST
  }
}
