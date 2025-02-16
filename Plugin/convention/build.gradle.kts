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
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11

    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

// Define the build configuration
tasks.register("generateBuildConfig") {
    doLast {
        val outputFile = file("src/main/kotlin/generated/VersionCatalog.kt")
        outputFile.parentFile.mkdirs()
        outputFile.writeText(
            """

            object VersionCatalog {
                val TARGET_SDK = ${libs.versions.android.targetSdk.get()}
                val COMPILE_SDK = ${libs.versions.android.compileSdk.get()}
                val MIN_SDK = ${libs.versions.android.minSdk.get()}

                // JAVA
                val JAVA_SOURCE_COMPAT = org.gradle.api.JavaVersion.VERSION_11
                val JAVA_TARGET_COMPAT = org.gradle.api.JavaVersion.VERSION_11
                val JVM_TARGET = "${libs.versions.jvm.target.get()}"

                // KOTLIN
                val KOTLIN_VERSION = "${libs.versions.kotlin.get()}"
                /*val KOTLIN_COMPILER_EXTENSION_VERSION = "1.5.10"*/

                // JUNIT
                val JUNIT_VERSION = "${libs.versions.junit.get()}"
            }
            """.trimIndent()
        )
    }
}

tasks.named("compileKotlin") {
    dependsOn("generateBuildConfig")
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.gradle)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.compose.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("AppConventionV1") {
            id = "AppConventionV1"
            implementationClass = "plugin.convention.AppConventionV1"
        }
        register("LibraryConventionV1") {
            id = "LibraryConventionV1"
            implementationClass = "plugin.convention.LibraryConventionV1"
        }
        register("CompileIOS") {
            id = "CompileIOS"
            implementationClass = "plugin.convention.CompileIOS"
        }
        register("CompileWasm") {
            id = "CompileWasm"
            implementationClass = "plugin.convention.CompileWasm"
        }
        register("FeatureScreen") {
            id = "FeatureScreen"
            implementationClass = "plugin.convention.features.FeatureScreen"
        }
        register("FeatureContextReceiver") {
            id = "FeatureContextReceiver"
            implementationClass = "plugin.convention.features.FeatureContextReceiver"
        }
        register("FeatureHttpClient") {
            id = "FeatureHttpClient"
            implementationClass = "plugin.convention.features.FeatureHttpClient"
        }
        register("FeatureScreenNavigation") {
            id = "FeatureScreenNavigation"
            implementationClass = "plugin.convention.features.FeatureScreenNavigation"
        }
        register("FeatureSerialization") {
            id = "FeatureSerialization"
            implementationClass = "plugin.convention.features.FeatureSerialization"
        }
    }
}
