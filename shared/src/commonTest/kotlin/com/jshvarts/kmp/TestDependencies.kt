package com.jshvarts.kmp

import kotlin.reflect.KClass

/**
 * Still not fully sure how this works, but the common tests are on the JVM
 * using Android's JUnit runner so that an in-memory database can be created.
 * See [BaseDatabaseTest].
 *
 * Copied from https://github.com/russhwolf/soluna
 */
expect abstract class Runner
expect class AndroidJUnit4 : Runner

@Suppress("unused")
@OptionalExpectation
@OptIn(ExperimentalMultiplatform::class)
expect annotation class RunWith(val value: KClass<out Runner>)
