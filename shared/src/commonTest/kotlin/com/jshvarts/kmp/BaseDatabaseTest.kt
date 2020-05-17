package com.jshvarts.kmp
import com.jshvarts.kmp.db.KmpGithubDatabase
import com.squareup.sqldelight.db.SqlDriver
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

expect fun inMemorySqlDriver(): SqlDriver

internal fun SqlDriver.createPressDatabase() = KmpGithubDatabase(driver = this)

/**
 * Creates an in-memory database and closes it before and after each test.
 * This class exists because JUnit rule isn't a thing (yet) in Kotlin tests.
 */
open class BaseDatabaseTest {

  private lateinit var sqlDriver: SqlDriver
  private lateinit var _database: KmpGithubDatabase

  fun database(): KmpGithubDatabase {
    if (::_database.isInitialized) {
      return _database
    } else {
      throw IllegalStateException(
          "Test database isn't created because @BeforeTest hasn't been " +
              "called yet. Avoid storing the database as a class property."
      )
    }
  }

  @BeforeTest
  fun initDb() {
    println("init db")
    sqlDriver = inMemorySqlDriver()
    _database = sqlDriver.createPressDatabase()
  }

  @AfterTest
  fun closeDb() {
    sqlDriver.close()
  }
}
