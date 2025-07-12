import plugin.convention.companion.compileAndroidLibrary
import plugin.convention.companion.compileIOSLibrary
import plugin.convention.companion.dependency
import plugin.convention.companion.withKotlinMultiplatformExtension
import plugin.convention.companion.withLibraryExtension

plugins {
    id("ConventionUtils")
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.ksp)
}

compileAndroidLibrary(
    namespace = "com.singularityuniverse.singularity.main.core"
)

compileIOSLibrary(
    namespace = "com.singularityuniverse.singularity.main.core",
    baseName = "Core",
    isStatic = true
)

dependency {
    android {
        withKotlinMultiplatformExtension {
            api(compose.preview)
        }
        api(libs.androidx.activity.compose)

        // Android-specific HTTP client
        api(libs.ktor.client.okhttp)
        api(libs.ktor.client.android)
    }

    ios {
        api(libs.kotlin.test)

        // iOS-specific HTTP client
        api(libs.ktor.client.darwin)
    }

    common {
        withKotlinMultiplatformExtension {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            api(compose.ui)
            api(compose.components.resources)
            api(compose.components.uiToolingPreview)
        }
        api(libs.androidx.lifecycle.viewmodel)
        api(libs.androidx.lifecycle.runtimeCompose)

        // Navigation dependencies
        api(libs.navigation.compose)
        api(libs.kotlinx.serialization)

        // Image loading dependencies
        api(libs.coil.compose)
        api(libs.coil.network.ktor3)

        // Core of Orbit, providing state management and unidirectional data flow (multiplatform)
        api(libs.orbit.core)
        // Integrates Orbit with Android and Common ViewModel for lifecycle-aware state handling (Android, iOS, desktop)
        api(libs.orbit.viewmodel)
        // Enables Orbit support for Jetpack Compose and Compose Multiplatform (Android, iOS, desktop)
        api(libs.orbit.compose)

        // Koin
        api(project.dependencies.platform(libs.koin.bom))
        api(libs.koin.core)
        api(libs.koin.compose)
        api(libs.koin.viewmodel)

        // Ktor
        api(libs.ktor.client.logging)
        api(libs.ktor.client.content.negotiation)
        api(libs.ktor.serialization.kotlinx.json)

        // Room
        api(libs.room.runtime)
        api(libs.sqlite.bundled)
    }

    test {
        api(kotlin("test"))
        api(libs.orbit.test)
    }
}

dependencies {
    api(compose.uiTooling)

    // KSP support for Room Compiler.
    add("kspAndroid", libs.room.compiler)
    add("kspIosSimulatorArm64", libs.room.compiler)
    add("kspIosX64", libs.room.compiler)
    add("kspIosArm64", libs.room.compiler)
}
