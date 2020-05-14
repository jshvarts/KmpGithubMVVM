package com.jshvarts.kmp

expect fun platformName(): String

fun createPlatformMessage(): String {
  return "Kotlin rocks on ${platformName()}"
}
