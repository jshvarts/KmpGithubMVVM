package com.jshvarts.kmp.shared.presentation

interface BasePresenter {
  fun onError(error: Throwable)
  fun stop()
}
