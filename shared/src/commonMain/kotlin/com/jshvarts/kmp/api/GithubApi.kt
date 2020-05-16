package com.jshvarts.kmp.api

import com.jshvarts.kmp.model.Member
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class GithubApi {

  private val memberUrl = "https://api.github.com/orgs/jetbrains/members"

  private val client by lazy {
    HttpClient {
      install(JsonFeature) {
        serializer = KotlinxSerializer(
          Json(
            JsonConfiguration(isLenient = true, ignoreUnknownKeys = true)
          )
        )
      }
      install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
      }
    }
  }

  suspend fun getMembers(): List<Member> = client.get(memberUrl)
}
