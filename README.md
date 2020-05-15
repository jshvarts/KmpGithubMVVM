# Kotlin Multiplatform Sample

![Android CI](https://github.com/jshvarts/KmpGithubMVVM/workflows/CI/badge.svg)

* Implements Android and iOS targets
* Uses MVVM Design pattern (Lifecycle Arch component and LiveData on Android, SwiftUI and Combine on iOS)
* Shares Models and Repository layer
* Uses [Ktor Http client](https://ktor.io/clients/index.html) library for making remote calls
* Uses [SqlDelight](https://github.com/cashapp/sqldelight) library for local data persistence for both Android and iOS
* Uses Cocoapods plugin for building iOS targets. For cocoapods setup instructions, see [this readme](https://github.com/jshvarts/KmpGithub)

## Screenshots

Android                    |  iOS
:-------------------------:|:-------------------------:
![android](docs/android.png?raw=true) | ![ios](docs/ios.png)

## Installation

Execute `gradlew build` from the root of the project.

You should now be able to install the app on Android device and emulator and on iOS device and simulator.

Note: if you are unable to run the project in Xcode, you may need to add `-lsqlite3` to `Other Linker Flags` as mentioned in [this comment](https://github.com/cashapp/sqldelight/issues/1442#issuecomment-523435492)

Check out [KaMPKit](https://github.com/touchlab/KaMPKit) for more examples as well as unit tests
