package com.jshvarts.kmp

import co.touchlab.sqliter.DatabaseConfiguration
import com.jshvarts.kmp.db.KmpGithubDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.drivers.native.wrapConnection

actual fun inMemorySqlDriver(): SqlDriver = NativeSqliteDriver(
  DatabaseConfiguration(
      name = "members.test.db",
      version = 1,
      inMemory = true,
      create = { connection ->
        wrapConnection(connection) { KmpGithubDatabase.Schema.create(it) }
      },
      upgrade = { connection, oldVersion, newVersion ->
        wrapConnection(connection) { KmpGithubDatabase.Schema.migrate(it, oldVersion, newVersion) }
      }
  )
)
