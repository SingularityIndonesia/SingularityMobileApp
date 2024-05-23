/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
plugins {
    id("LibraryConventionV1")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs2.ktor.client.okhttp)
        }
        commonMain.dependencies {
            // ktor
            implementation(libs2.ktor.client.core)
            implementation(libs2.ktor.client.cio)

            implementation("system:core")
        }
        iosMain.dependencies {
            implementation(libs2.ktor.client.ios)
        }
    }
}

android {
    namespace = "shared.webrepository"
}

task("testClasses")