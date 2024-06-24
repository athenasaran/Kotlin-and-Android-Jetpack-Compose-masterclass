// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()

        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
        maven {
            url = uri("https://dl.google.com/dl/android/maven2")
        }
    }
    dependencies {
        classpath (libs.build.logic.android.gradlePlugin)
        classpath (libs.build.logic.kotlin.gradlePlugin)
    }
}

plugins {
    alias(libs.plugins.ksp) apply false
}

tasks.register("clean",Delete::class){
    delete(rootProject.layout.buildDirectory)
}
