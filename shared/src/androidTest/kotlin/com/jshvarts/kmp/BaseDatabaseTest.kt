@file:JvmName("BaseDatabaseTestAndroid")
package com.jshvarts.kmp

import androidx.test.core.app.ApplicationProvider
import com.jshvarts.kmp.db.KmpGithubDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

/**
 * The advantage of using this over a JdbcDriver is that manual
 * creation of the tables isn't required.
 */
actual fun inMemorySqlDriver(): SqlDriver = AndroidSqliteDriver(
    schema = KmpGithubDatabase.Schema,
    context = ApplicationProvider.getApplicationContext(),
    name = null // in-memory.
)
