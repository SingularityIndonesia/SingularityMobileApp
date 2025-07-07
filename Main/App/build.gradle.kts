import plugin.convention.companion.compileAndroidLibrary
import plugin.convention.companion.compileIOSLibrary
import plugin.convention.companion.dependency
import plugin.convention.companion.withKotlinMultiplatformExtension

plugins {
    id("Convention")
    kotlin("plugin.serialization") version "2.2.0"
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
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")
        
        // Image loading dependencies
        implementation(libs.coil.compose)
        implementation(libs.coil.network.ktor3)
    }

    test {
        implementation(kotlin("test"))
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

