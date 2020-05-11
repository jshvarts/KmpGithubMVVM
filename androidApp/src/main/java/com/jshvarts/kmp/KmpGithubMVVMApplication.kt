package com.jshvarts.kmp

import android.app.Application
import com.jshvarts.kmp.shared.BuildConfig
import com.jshvarts.kmp.shared.api.GithubApi
import com.jshvarts.kmp.shared.model.MembersRepository
import timber.log.Timber

class KmpGithubMVVMApplication : Application() {
  val membersRepository: MembersRepository by lazy {
    MembersRepository(GithubApi())
  }

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}
