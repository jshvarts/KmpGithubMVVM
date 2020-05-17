package com.jshvarts.kmp
import com.jshvarts.kmp.db.KmpGithubDatabase
import com.squareup.sqldelight.db.SqlDriver
import kotlin.test.AfterTest

expect fun inMemorySqlDriver(): SqlDriver

internal fun SqlDriver.createDatabase() = KmpGithubDatabase(driver = this)

/**
 * Creates an in-memory database and closes it before and after each test.
 * This class exists because JUnit rule isn't a thing (yet) in Kotlin tests.
 */
open class BaseDatabaseTest {
  private val sqlDriver: SqlDriver = inMemorySqlDriver()
  protected val database: KmpGithubDatabase = sqlDriver.createDatabase()

  @AfterTest
  fun closeDb() {
    sqlDriver.close()
  }
}
