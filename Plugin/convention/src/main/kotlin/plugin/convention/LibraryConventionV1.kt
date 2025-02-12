/*
 * Copyright (c) 2024 Singularity Indonesia (stefanus.ayudha@gmail.com)
 * You are not allowed to remove the copyright. Unless you have a "free software" licence.
 */
package plugin.convention

import VersionCatalog.COMPILE_SDK
import VersionCatalog.JAVA_SOURCE_COMPAT
import VersionCatalog.JAVA_TARGET_COMPAT
import VersionCatalog.JUNIT_VERSION
import VersionCatalog.JVM_TARGET
import VersionCatalog.MIN_SDK
import VersionCatalog.TARGET_SDK
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import plugin.convention.companion.DefaultConfigs.EXCLUDED_RESOURCES

class LibraryConventionV1 : Plugin<Project> {

    companion object {
        public val ID: String = "LibraryConventionV1"
    }

    private val PLUGINS = listOf(
        "com.android.library",
        "org.jetbrains.kotlin.multiplatform",
    )

    override fun apply(target: Project) =
        with(target) {
            with(pluginManager) {
                PLUGINS.forEach(::apply)
            }

            extensions.configure<KotlinMultiplatformExtension> {
                androidTarget {
                    compilations.all {
                        kotlinOptions {
                            jvmTarget = JVM_TARGET
                        }
                    }
                }

                sourceSets.commonTest.dependencies {
                    implementation("junit:junit:$JUNIT_VERSION")
                }
            }

            extensions.configure<LibraryExtension> {
                compileSdk = COMPILE_SDK

                sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
                sourceSets["main"].res.srcDirs("src/androidMain/res")
                sourceSets["main"].resources.srcDirs("src/commonMain/resources")

                defaultConfig {
                    minSdk = MIN_SDK
                    targetSdk = TARGET_SDK
                }
                packaging {
                    resources {
                        excludes += EXCLUDED_RESOURCES
                    }
                }
                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                    }
                }
                compileOptions {
                    sourceCompatibility = JAVA_SOURCE_COMPAT
                    targetCompatibility = JAVA_TARGET_COMPAT
                }
            }
        }

}