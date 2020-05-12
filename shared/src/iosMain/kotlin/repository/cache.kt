package com.jshvarts.kmp.shared.repository

import com.jshvarts.kmp.db.KmpGithubDatabase
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

internal actual fun cache(): KmpGithubDatabase {
  val driver = NativeSqliteDriver(KmpGithubDatabase.Schema, "members.db")
  return KmpGithubDatabase(driver)
}
