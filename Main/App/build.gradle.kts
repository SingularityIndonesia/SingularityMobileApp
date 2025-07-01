import plugin.convention.companion.compileAndroidLibrary
import plugin.convention.companion.compileIOSLibrary
import plugin.convention.companion.dependency
import plugin.convention.companion.withKotlinMultiplatformExtension

plugins {
    id("Convention")
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
    }

    ios {
        implementation(libs.kotlin.test)
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
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

