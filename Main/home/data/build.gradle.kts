plugins {
    id("LibraryConventionV1")
    id("CompileIOS")
    id("FeatureSerialization")
    id("FeatureHttpClient")
}

kotlin {
    sourceSets {
        androidMain.dependencies {

        }
        commonMain.dependencies {

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)

            implementation(libs.kotlinx.serialization.json)

            implementation("system:core")
            implementation("shared:common")
            implementation("shared:webrepository")

            implementation(project(":home:model"))
        }
        iosMain.dependencies {
          implementation(libs.ktor.client.ios)
        }
    }
}


android {


    namespace = "main.home.data"
    dependencies {

    }
}

task("testClasses")
