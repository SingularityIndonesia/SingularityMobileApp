plugins {
    id("LibraryConventionV1")
    id("CompileIOS")
    id("FeatureScreen")
    id("FeatureSerialization")
    id("FeatureHttpClient")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("system:core")
            implementation("system:designsystem")
            implementation("shared:common")

            implementation(project(":home:data"))
            implementation(project(":home:model"))
        }
    }
}

android {
    namespace = "main.home.presentation"
}

task("testClasses")
