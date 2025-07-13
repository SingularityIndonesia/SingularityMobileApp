plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinxSerialization)
    application
}

group = "com.singularityuniverse.memories.mpai"
version = "1.0.0"

application {
    mainClass.set("ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")

    kotlin {
        compilerOptions {
            freeCompilerArgs.add("-Xcontext-parameters")
        }
    }
}

dependencies {
    implementation(project(":infra"))
    api(project(":core"))

    // Test dependencies
    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)
    testImplementation(libs.ktor.clientContentNegotiation)
}