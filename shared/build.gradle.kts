plugins {
  kotlin("multiplatform")
  id("kotlinx-serialization")
  id("com.android.library")
  id("org.jetbrains.kotlin.native.cocoapods")
  id("com.squareup.sqldelight")
}

android {
  compileSdkVersion(Versions.compileSdk)
  buildToolsVersion(Versions.androidBuildTools)

  defaultConfig {
    minSdkVersion(Versions.minSdk)
    targetSdkVersion(Versions.targetSdk)
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
}

// CocoaPods requires the podspec to have a version.
version = "1.0"

kotlin {
  targets {
    val sdkName: String? = System.getenv("SDK_NAME")

    val isiOSDevice = sdkName.orEmpty().startsWith("iphoneos")
    if (isiOSDevice) {
      iosArm64("iOS")
    } else {
      iosX64("iOS")
    }

    android()
  }

  cocoapods {
    // Configure fields required by CocoaPods.
    summary = "Description for a Kotlin/Native module"
    homepage = "Link to a Kotlin/Native module homepage"
  }

  sourceSets {
    all {
      languageSettings.apply {
        useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
        useExperimentalAnnotation("kotlinx.serialization.UnstableDefault")
      }
    }

    val commonMain by getting {
      dependencies {
        implementation(kotlin("stdlib-common"))
        implementation(Coroutines.Core.common)
        implementation(Ktor.Core.common)
        implementation(Ktor.Json.common)
        implementation(Ktor.Logging.common)
        implementation(Ktor.Serialization.common)
        implementation(SqlDelight.runtime)
      }
    }

    val commonTest by getting {
      dependencies {
        implementation(kotlin("test-annotations-common"))
        implementation(kotlin("test-common"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.6")
        implementation(Ktor.Mock.common)
      }
    }

    val androidMain by getting {
      dependencies {
        implementation(kotlin("stdlib"))
        implementation(Coroutines.Core.core)
        implementation(Ktor.android)
        implementation(Ktor.Core.jvm)
        implementation(Ktor.Json.jvm)
        implementation(Ktor.Logging.jvm)
        implementation(Ktor.Logging.slf4j)
        implementation(Ktor.Mock.jvm)
        implementation(Ktor.Serialization.jvm)
        implementation(Serialization.runtime)
        implementation(SqlDelight.android)
      }
    }

    val androidTest by getting {
      dependencies {
        implementation(kotlin("test"))
        implementation(kotlin("test-junit"))
        implementation("junit:junit:4.13")
        implementation("androidx.test:core:1.2.0")
        implementation("androidx.test.ext:junit:1.1.1")
        implementation("androidx.test:runner:1.2.0")
        implementation("androidx.test:rules:1.2.0")
        implementation("org.robolectric:robolectric:4.3.1")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.6")
        implementation(Ktor.Mock.jvm)
      }
    }

    val iOSMain by getting {
      dependencies {
        implementation(Coroutines.Core.native)
        implementation(Ktor.ios)
        implementation(Ktor.Core.native)
        implementation(Ktor.Json.native)
        implementation(Ktor.Logging.native)
        implementation(Ktor.Serialization.native)
        implementation(Serialization.runtimeNative)
        implementation(SqlDelight.native)
        implementation(Ktor.Mock.native)
      }
    }

    val iOSTest by getting {
      dependencies {
        implementation(Ktor.Mock.native)
      }
    }
  }
}

sqldelight {
  database("KmpGithubDatabase") {
    packageName = "com.jshvarts.kmp.db"
    sourceFolders = listOf("sqldelight")
  }
}
