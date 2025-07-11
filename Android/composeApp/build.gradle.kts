import plugin.convention.companion.Main
import plugin.convention.companion.compileAndroidApplication
import plugin.convention.companion.dependency
import plugin.convention.companion.envProp
import plugin.convention.companion.withKotlinMultiplatformExtension

plugins {
    id("ConventionUtils")
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

val versionCode = 1
val versionName = "1.0.0"

envProp("env.properties") {
    put("VERSION_CODE",versionCode.toString())
    put("VERSION_NAME",versionName)
}

compileAndroidApplication(
    namespace = "com.singularityuniverse.singularity.android",
    applicationId = "com.singularityuniverse.singularity.android",
    versionCode = versionCode,
    versionName = versionName,
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
        Main("App", true)
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

