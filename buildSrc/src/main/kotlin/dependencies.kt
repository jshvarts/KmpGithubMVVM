object Versions {
  const val androidBuildTools = "29.0.2"
  const val appCompat = "1.0.2"
  const val compileSdk = 29
  const val constraintLayout = "1.1.3"
  const val coroutines = "1.3.5"
  const val kotlin = "1.3.72"
  const val ktor = "1.3.2"
  const val lifecycle = "2.2.0"
  const val material = "1.2.0-alpha05"
  const val minSdk = 23
  const val picasso = "2.71828"
  const val recyclerView = "1.1.0"
  const val serialization = "0.20.0"
  const val slf4j = "1.7.30"
  const val sqldelight = "1.3.0"
  const val swipeToRefreshLayout = "1.0.0"
  const val targetSdk = 29
  const val timber = "4.7.1"
}

const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
const val material = "com.google.android.material:material:${Versions.material}"

object AndroidX {
  const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
  const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
  const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
  const val lifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
  const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
  const val swipeToRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeToRefreshLayout}"
}

object Coroutines {
  const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.ktor}"

  object Core {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.coroutines}"
    const val native = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.coroutines}"
  }
}

object Ktor {
  const val android = "io.ktor:ktor-client-android:${Versions.ktor}"
  const val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"

  object Core {
    const val common = "io.ktor:ktor-client-core:${Versions.ktor}"
    const val jvm = "io.ktor:ktor-client-core-jvm:${Versions.ktor}"
    const val native = "io.ktor:ktor-client-core-native:${Versions.ktor}"
  }

  object Json {
    const val common = "io.ktor:ktor-client-json:${Versions.ktor}"
    const val jvm = "io.ktor:ktor-client-json-jvm:${Versions.ktor}"
    const val native = "io.ktor:ktor-client-json-native:${Versions.ktor}"
  }

  object Logging {
    const val common = "io.ktor:ktor-client-logging:${Versions.ktor}"
    const val jvm = "io.ktor:ktor-client-logging-jvm:${Versions.ktor}"
    const val slf4j = "org.slf4j:slf4j-simple:${Versions.slf4j}"
    const val native = "io.ktor:ktor-client-logging-native:${Versions.ktor}"
  }

  object Serialization {
    const val common = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    const val jvm = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
    const val native = "io.ktor:ktor-client-serialization-native:${Versions.ktor}"
  }
}

object Serialization {
  const val runtime =
      "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.serialization}"
  const val runtimeNative =
      "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${Versions.serialization}"
}

object SqlDelight {
  const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqldelight}"
  const val android = "com.squareup.sqldelight:android-driver:${Versions.sqldelight}"
  const val native = "com.squareup.sqldelight:native-driver:${Versions.sqldelight}"
}

