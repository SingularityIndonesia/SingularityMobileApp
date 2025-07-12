import plugin.convention.companion.compileAndroidLibrary
import plugin.convention.companion.compileIOSLibrary
import plugin.convention.companion.dependency

plugins {
    id("ConventionUtils")
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

compileAndroidLibrary(
    namespace = "com.singularityuniverse.singularity.main.app"
)

compileIOSLibrary(
    namespace = "com.singularityuniverse.singularity.main.app",
    baseName = "MainApp",
    isStatic = true
)

dependency {
    common {
        api(project(":Service"))
        api(project(":Font"))
        api(project(":Core"))
    }
}
