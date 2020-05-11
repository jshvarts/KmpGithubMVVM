package com.jshvarts.kmp.shared.model

import com.jshvarts.kmp.db.KmpGithubDatabase
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual fun createDb(): KmpGithubDatabase {
  val driver = NativeSqliteDriver(KmpGithubDatabase.Schema, "members.db")
  return KmpGithubDatabase(driver)
}
