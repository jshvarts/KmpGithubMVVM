package com.jshvarts.kmp.shared.repository

import android.content.Context
import com.jshvarts.kmp.db.KmpGithubDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver

lateinit var appContext: Context

internal actual fun cache(): KmpGithubDatabase {
  val driver = AndroidSqliteDriver(KmpGithubDatabase.Schema, appContext, "members.db")
  return KmpGithubDatabase(driver)
}
