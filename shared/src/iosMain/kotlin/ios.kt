package com.jshvarts.kmp.shared

import platform.UIKit.UIDevice

actual fun platformName(): String {
    val systemName = UIDevice.currentDevice.systemName()
    val systemVersion = UIDevice.currentDevice.systemVersion

    return "$systemName $systemVersion"
}
