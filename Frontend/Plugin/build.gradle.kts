plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    // alias(libs.plugins.androidApplication) apply false
    // alias(libs.plugins.androidLibrary) apply false
    // alias(libs.plugins.jetbrainsCompose) apply false
    // alias(libs.plugins.compose.compiler) apply false
    // alias(libs.plugins.kotlin.all.open) apply false
    // alias(libs.plugins.kotlinxSerialization) apply false
    // alias(libs.plugins.kotlinMultiplatform) apply false
    // alias(libs.plugins.devtools.ksp) apply false

    alias(libs.plugins.kotlinxSerialization) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}
