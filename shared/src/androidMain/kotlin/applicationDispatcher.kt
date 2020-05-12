package com.jshvarts.kmp.shared

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

internal actual val applicationDispatcher: CoroutineContext = Dispatchers.IO
