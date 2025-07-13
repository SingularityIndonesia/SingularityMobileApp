plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinxSerialization)
    application
}

group = "com.singularityuniverse.memories.core"
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
    
    api(libs.logback)
    api(libs.ktor.serverCore)
    api(libs.ktor.serverNetty)
    api(libs.ktor.serverContentNegotiation)
    api(libs.ktor.serializationKotlinxJson)

    // Test dependencies
    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)
    testImplementation(libs.ktor.clientContentNegotiation)
}