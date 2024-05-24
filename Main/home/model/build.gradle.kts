plugins {
    id("LibraryConventionV1")
    kotlin("plugin.serialization")
}

kotlin {
    sourceSets {
        androidMain.dependencies {

        }
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
        }
        iosMain.dependencies {

        }
    }
}

android {
    namespace = "main.home.model"
    dependencies {

    }
}

task("testClasses")
