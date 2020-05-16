package com.jshvarts.kmp.api

import com.jshvarts.kmp.model.Member
import com.jshvarts.kmp.runTest
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.json.JsonFeature
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GithubApiTest {

  @Test
  fun `when client throws error, throws exception`() = runTest {
    // GIVEN
    val mockEngine = MockEngine { respondError(HttpStatusCode.NotFound) }

    val httpClient = HttpClient(mockEngine) {
      install(JsonFeature) {
        serializer = kotlinxSerializer
      }
    }

    val githubApi = GithubApi(httpClient)

    // WHEN/THEN
    assertFailsWith<ClientRequestException> { githubApi.getMembers() }
  }

  @Test
  fun `when client succeeds, returns expected members`() = runTest {
    // GIVEN
    val testResponseString = """
    [{
      "login": "someuser",
      "id": 1,
      "avatar_url": "http://www.example.com/avatar.png"
    }]     
    """.trimIndent()

    val mockEngine = MockEngine {
      respond(testResponseString,
          HttpStatusCode.OK,
          headersOf("Content-Type", ContentType.Application.Json.toString()))
    }

    val httpClient = HttpClient(mockEngine) {
      install(JsonFeature) {
        serializer = kotlinxSerializer
      }
    }

    val githubApi = GithubApi(httpClient)

    val expectedMembers = listOf(
        Member(
            id = 1L,
            login = "someuser",
            avatarUrl = "http://www.example.com/avatar.png"
        )
    )

    // WHEN
    val actualMembers = githubApi.getMembers()

    // THEN
    assertEquals(expectedMembers, actualMembers)
  }

  @Test
  fun `when json has unknown fields, returns expected members`() = runTest {
    // GIVEN
    val testResponseString = """
    [{
      "login": "someuser",
      "id": 1,
      "avatar_url": "http://www.example.com/avatar.png",
      "unknown_field": "foo"
    }]     
    """.trimIndent()

    val mockEngine = MockEngine {
      respond(testResponseString,
          HttpStatusCode.OK,
          headersOf("Content-Type", ContentType.Application.Json.toString()))
    }

    val httpClient = HttpClient(mockEngine) {
      install(JsonFeature) {
        serializer = kotlinxSerializer
      }
    }

    val githubApi = GithubApi(httpClient)

    val expectedMembers = listOf(
        Member(
            id = 1L,
            login = "someuser",
            avatarUrl = "http://www.example.com/avatar.png"
        )
    )

    // WHEN
    val actualMembers = githubApi.getMembers()

    // THEN
    assertEquals(expectedMembers, actualMembers)
  }
}
