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
    baseName = "Font",
    isStatic = true
)

compose.resources {
    publicResClass = true
    packageOfResClass = "font.resources"
    generateResClass = auto
}

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
        implementation(libs.androidx.lifecycle.runtimeCompose)

        // Image loading dependencies
        implementation(libs.coil.compose)
        implementation(libs.coil.network.ktor3)
    }

    test {
        implementation(kotlin("test"))
        implementation(libs.orbit.test)
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}