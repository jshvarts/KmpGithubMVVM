// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.6.3")
        classpath(kotlin("gradle-plugin", version = "${Versions.kotlin}"))
        classpath(kotlin("serialization", version = "${Versions.kotlin}"))
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
}

apply(from = "quality/lint.gradle") 
