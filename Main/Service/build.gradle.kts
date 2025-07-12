import plugin.convention.companion.compileAndroidLibrary
import plugin.convention.companion.compileIOSLibrary
import plugin.convention.companion.dependency
import plugin.convention.companion.withKotlinMultiplatformExtension

plugins {
    id("ConventionUtils")
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

compileAndroidLibrary(
    namespace = "com.singularityuniverse.singularity.main"
)

compileIOSLibrary(
    namespace = "com.singularityuniverse.singularity.main",
    baseName = "Data",
    isStatic = true
)

dependency {
    common {
        api(project(":Core"))
    }
}
