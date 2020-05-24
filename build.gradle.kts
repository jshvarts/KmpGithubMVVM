// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.6.3")
        classpath(kotlin("gradle-plugin", version = Versions.kotlin))
        classpath(kotlin("serialization", version = Versions.kotlin))
        classpath("com.squareup.sqldelight:gradle-plugin:${Versions.sqldelight}")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.28.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

plugins {
  id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
  id ("com.github.ben-manes.versions") version "0.28.0"
}

apply(from = "quality/lint.gradle") 
