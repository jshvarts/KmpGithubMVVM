package com.jshvarts.kmp.api

import com.jshvarts.kmp.model.Member

internal const val MEMBERS_URL = "https://api.github.com/orgs/jetbrains/members"

interface GithubApi {
  suspend fun getMembers(): List<Member>
}
