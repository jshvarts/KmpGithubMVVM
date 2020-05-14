package com.jshvarts.kmp.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Member(
  val id: Long,
  val login: String,
  @SerialName("avatar_url") val avatarUrl: String
)
