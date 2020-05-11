package com.jshvarts.kmp.shared.model

import android.content.Context
import com.jshvarts.kmp.db.KmpGithubDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver

lateinit var appContext: Context

actual fun createDb(): KmpGithubDatabase {
  val driver = AndroidSqliteDriver(KmpGithubDatabase.Schema, appContext, "members.db")
  return KmpGithubDatabase(driver)
}
