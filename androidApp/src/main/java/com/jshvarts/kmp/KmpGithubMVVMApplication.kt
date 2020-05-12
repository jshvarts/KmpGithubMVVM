package com.jshvarts.kmp

import android.app.Application
import com.jshvarts.kmp.shared.BuildConfig
import com.jshvarts.kmp.shared.repository.MembersRepository
import com.jshvarts.kmp.shared.repository.appContext
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

class KmpGithubMVVMApplication : Application() {
  @ExperimentalCoroutinesApi
  val membersRepository by lazy { MembersRepository() }

  override fun onCreate() {
    super.onCreate()

    appContext = this

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}
