package com.jshvarts.kmp.shared

import kotlin.coroutines.CoroutineContext

expect fun platformName(): String

fun createPlatformMessage(): String {
  return "Kotlin rocks on ${platformName()}"
}

internal expect val ApplicationDispatcher: CoroutineContext
