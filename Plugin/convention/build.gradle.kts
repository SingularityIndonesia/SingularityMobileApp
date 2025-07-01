/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
plugins {
    `kotlin-dsl`
    id("org.jetbrains.kotlin.plugin.serialization")
}

group = "plugin.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.2.0")
    implementation("com.android.tools.build:gradle:8.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    implementation("org.jetbrains.compose:compose-gradle-plugin:1.8.2")
}

gradlePlugin {
    plugins {
        register("Convention") {
            id = "Convention"
            implementationClass = "plugin.convention.Convention"
        }
    }
}
