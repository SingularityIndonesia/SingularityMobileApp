package plugin.convention.companion

import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke
import java.io.File

fun Project.envProp(fileName: String) {
    File(project.projectDir, fileName).bufferedReader().useLines {
        val props = it
            .filter { it.contains("=") }
            .map { it.split("=") }

        val fileProto = """
            object EnvironmentProperties {
                ${props.joinToString("\n                ") { "val ${it.first()} = \"${it[1]}\"" }}
            }
        """.trimIndent()

        val targetDir =
            File(project.projectDir, "build/generated/envprop/kotlin/")
        targetDir.mkdirs()

        val targetFile = File(targetDir, "EnvironmentProperties.kt")
        if (!targetFile.exists()) {
            targetFile.createNewFile()
        }
        targetFile.writeText(fileProto)
    }

    withKotlinMultiplatformExtension {
        sourceSets {
            commonMain {
                kotlin.srcDirs("build/generated/envprop/kotlin/")
            }
        }
    }
}