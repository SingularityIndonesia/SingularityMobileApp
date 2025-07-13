plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinxSerialization)
    application
}

group = "com.singularityuniverse.memories.infra"
version = "1.0.0"

application {

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")

    kotlin {
        compilerOptions {
            freeCompilerArgs.add("-Xcontext-parameters")
        }
    }
}

dependencies {
    api(project(":core"))

    // Database dependencies
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.exposed.kotlinDatetime)
    implementation(libs.exposed.json)
    implementation(libs.postgresql)
    implementation(libs.h2) // for testing
    implementation(libs.hikari)

    // Test dependencies
    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)
    testImplementation(libs.ktor.clientContentNegotiation)
}