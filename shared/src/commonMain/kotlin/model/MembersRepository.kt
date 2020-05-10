package com.jshvarts.kmp.shared.model

interface MembersRepository {
  suspend fun getMembers(): List<Member>
}
