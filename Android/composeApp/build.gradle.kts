import plugin.convention.companion.Main
import plugin.convention.companion.compileAndroidApplication
import plugin.convention.companion.dependency
import plugin.convention.companion.withKotlinMultiplatformExtension

plugins {
    id("ConventionUtils")
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

compileAndroidApplication(
    namespace = "com.singularityuniverse.singularity.android",
    applicationId = "com.singularityuniverse.singularity.android",
    versionCode = 1,
    versionName = "1.0",
)

dependency {
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
        Main("App")
        Main("Core")
    }

    android {
        withKotlinMultiplatformExtension {
            implementation(compose.preview)
        }
        implementation(libs.androidx.activity.compose)
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

