buildscript {
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.android.application.plugin) apply false
    alias(libs.plugins.android.library.plugin) apply false
    alias(libs.plugins.kotlin.android.plugin) apply false
    alias(libs.plugins.dagger.hilt.module.plugin) apply false
}