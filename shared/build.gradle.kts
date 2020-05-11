plugins {
  kotlin("multiplatform")
  id("kotlinx-serialization")
  id("com.android.library")
  id("org.jetbrains.kotlin.native.cocoapods")
}

android {
  compileSdkVersion(Versions.compileSdk)
  buildToolsVersion(Versions.androidBuildTools)

  defaultConfig {
    minSdkVersion(Versions.minSdk)
    targetSdkVersion(Versions.targetSdk)
    versionCode = 1
    versionName = "1.0"
  }
}

// CocoaPods requires the podspec to have a version.
version = "1.0"

kotlin {
  targets {
    val sdkName: String? = System.getenv("SDK_NAME")

    val isiOSDevice = sdkName.orEmpty().startsWith("iphoneos")
    if (isiOSDevice) {
      iosArm64("iOS64")
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
    val commonMain by getting {
      dependencies {
        implementation(kotlin("stdlib-common"))
        implementation(Coroutines.Core.common)
        implementation(Ktor.Core.common)
        implementation(Ktor.Json.common)
        implementation(Ktor.Logging.common)
        implementation(Ktor.Serialization.common)
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
        implementation(Ktor.Serialization.jvm)
        implementation(Serialization.runtime)
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
      }
    }
  }
}
