/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
import plugin.convention.companion.env

plugins {
    id("AppConventionV1")
    id("CompileIOS")
    id("FeatureScreen")
    id("FeatureScreenNavigation")
    id("FeatureSerialization")
    id("FeatureHttpClient")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("system:core")
            implementation("system:designsystem")
            implementation("shared:common")
            implementation("shared:webrepository")

            implementation(project(":example:presentation"))
            implementation(project(":example:model"))
        }
    }
}

composeCompiler {
    enableStrongSkippingMode = true
    stabilityConfigurationFile = project.rootDir.resolve("stability_config.conf")
}

android {
    namespace = "com.singularity.basemobile"
    defaultConfig {
        applicationId = "com.singularity.basemobile"
        versionCode = 1
        versionName = "1.0"
    }
}

android.buildTypes {
    getByName("debug") {
        buildConfigField("String", "HOST", env.getProperty("DEV_HOST"))
        buildConfigField("String", "API_BASE_PATH", env.getProperty("DEV_API_BASE_PATH"))
    }
    getByName("release") {
        buildConfigField("String", "HOST", env.getProperty("DEV_HOST"))
        buildConfigField("String", "API_BASE_PATH", env.getProperty("DEV_API_BASE_PATH"))
    }
    getByName("devDebug") {
        buildConfigField("String", "HOST", env.getProperty("DEV_HOST"))
        buildConfigField("String", "API_BASE_PATH", env.getProperty("DEV_API_BASE_PATH"))
    }

    getByName("devRelease") {
        buildConfigField("String", "HOST", env.getProperty("DEV_HOST"))
        buildConfigField("String", "API_BASE_PATH", env.getProperty("DEV_API_BASE_PATH"))
    }

    getByName("stagingDebug") {
        buildConfigField("String", "HOST", env.getProperty("STAGE_HOST"))
        buildConfigField("String", "API_BASE_PATH", env.getProperty("STAGE_API_BASE_PATH"))
    }

    getByName("stagingRelease") {
        buildConfigField("String", "HOST", env.getProperty("STAGE_HOST"))
        buildConfigField("String", "API_BASE_PATH", env.getProperty("STAGE_API_BASE_PATH"))
    }

    getByName("prodDebug") {
        buildConfigField("String", "HOST", env.getProperty("PROD_HOST"))
        buildConfigField("String", "API_BASE_PATH", env.getProperty("PROD_API_BASE_PATH"))
    }

    getByName("prodRelease") {
        buildConfigField("String", "HOST", env.getProperty("PROD_HOST"))
        buildConfigField("String", "API_BASE_PATH", env.getProperty("PROD_API_BASE_PATH"))
    }
}

task("testClasses")