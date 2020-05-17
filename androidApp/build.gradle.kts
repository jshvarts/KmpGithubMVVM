plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("android.extensions")
}

android {
  compileSdkVersion(Versions.compileSdk)

  defaultConfig {
    applicationId = "com.jshvarts.kmp.android"
    minSdkVersion(Versions.minSdk)
    targetSdkVersion(Versions.targetSdk)
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }

  packagingOptions {
    exclude("META-INF/*.kotlin_module")
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
  implementation(kotlin("stdlib-jdk7", Versions.kotlin))
  implementation(Coroutines.android)
  implementation(AndroidX.appCompat)
  implementation(AndroidX.constraintLayout)
  implementation(AndroidX.recyclerView)
  implementation(AndroidX.lifecycleExtensions)
  implementation(AndroidX.lifecycleViewModelKtx)
  implementation(material)
  implementation(AndroidX.swipeToRefreshLayout)
  implementation(timber)
  implementation(picasso)

  testImplementation(junit)

  androidTestImplementation(AndroidX.Test.core)
  androidTestImplementation(AndroidX.Test.runner)
  androidTestImplementation(AndroidX.Test.jUnit)
  androidTestImplementation(AndroidX.Test.jUnitKtx)

  implementation(project(":shared"))
}
