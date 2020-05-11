package com.jshvarts.kmp

import android.app.Application
import com.jshvarts.kmp.shared.BuildConfig
import com.jshvarts.kmp.shared.api.GithubApi
import com.jshvarts.kmp.shared.model.MembersRepository
import com.jshvarts.kmp.shared.model.appContext
import timber.log.Timber

class KmpGithubMVVMApplication : Application() {
  val membersRepository: MembersRepository by lazy {
    MembersRepository(GithubApi())
  }

  override fun onCreate() {
    super.onCreate()

    appContext = this

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}
