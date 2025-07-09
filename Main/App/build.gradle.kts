import plugin.convention.companion.compileAndroidLibrary
import plugin.convention.companion.compileIOSLibrary
import plugin.convention.companion.dependency
import plugin.convention.companion.withKotlinMultiplatformExtension

plugins {
    id("ConventionUtils")
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

compileAndroidLibrary(
    namespace = "com.singularityuniverse.singularity.main"
)

compileIOSLibrary(
    namespace = "com.singularityuniverse.singularity.main",
    baseName = "MainApp",
    isStatic = true
)

dependency {
    android {
        withKotlinMultiplatformExtension {
            implementation(compose.preview)
        }
        implementation(libs.androidx.activity.compose)

        // Android-specific HTTP client
        implementation(libs.ktor.client.okhttp)
    }

    ios {
        implementation(libs.kotlin.test)

        // iOS-specific HTTP client
        implementation(libs.ktor.client.darwin)
    }

    common {
        withKotlinMultiplatformExtension {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }
        implementation(libs.androidx.lifecycle.viewmodel)
        implementation(libs.androidx.lifecycle.runtimeCompose)

        // Navigation dependencies
        implementation(libs.navigation.compose)
        implementation(libs.kotlinx.serialization)

        // Image loading dependencies
        implementation(libs.coil.compose)
        implementation(libs.coil.network.ktor3)

        // Core of Orbit, providing state management and unidirectional data flow (multiplatform)
        implementation(libs.orbit.core)
        // Integrates Orbit with Android and Common ViewModel for lifecycle-aware state handling (Android, iOS, desktop)
        implementation(libs.orbit.viewmodel)
        // Enables Orbit support for Jetpack Compose and Compose Multiplatform (Android, iOS, desktop)
        implementation(libs.orbit.compose)
        implementation(project(":Data"))
    }

    test {
        implementation(kotlin("test"))
        implementation(libs.orbit.test)
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

