package com.jshvarts.kmp

import com.jshvarts.kmp.db.KmpGithubDatabase
import com.squareup.sqldelight.db.SqlDriver
import kotlin.test.AfterTest

expect fun inMemorySqlDriver(): SqlDriver

/**
 * Creates an in-memory database and closes it before and after each test.
 * This class exists because JUnit rules aren't a thing (yet) in Kotlin tests.
 * The name of this class is not a typo.
 */
open class BaseDatabaseTest {

  private val sqlDriver: SqlDriver = inMemorySqlDriver()
  protected val database: KmpGithubDatabase = sqlDriver.createDatabase()

  @AfterTest
  fun closeDb() {
    sqlDriver.close()
  }
}

private fun SqlDriver.createDatabase(): KmpGithubDatabase {
  return KmpGithubDatabase(driver = this)
}
