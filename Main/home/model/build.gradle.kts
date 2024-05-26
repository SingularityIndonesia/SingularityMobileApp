plugins {
    id("LibraryConventionV1")
    id("CompileIOS")
    id("FeatureSerialization")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
        }
    }
}

android {
    namespace = "main.home.model"
}

task("testClasses")
