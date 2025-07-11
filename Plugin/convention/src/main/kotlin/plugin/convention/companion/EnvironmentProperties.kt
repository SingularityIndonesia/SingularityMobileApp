package plugin.convention.companion

import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke
import java.io.File

fun Project.envProp(fileName: String) {
    withKotlinMultiplatformExtension {
        sourceSets {
            commonMain {
                kotlin.srcDirs("build/generated/envprop/kotlin/")
            }
        }
    }

    val targetFile = File(project.projectDir, fileName)

    val outputDir = File(project.projectDir, "build/generated/envprop/kotlin/")
        .also {
            if (!it.exists()) {
                it.mkdirs()
            }
        }

    val outputFile = File(outputDir, "EnvironmentProperties.kt")
        .also {
            if (!it.exists()) {
                it.createNewFile()
            }
        }

    val oldSum = outputFile.useLines {
        it.firstOrNull { it.contains("// SUM: ") }
            ?.split("// SUM: ")
            ?.get(1)
    }

    val newFileSum: String = targetFile.lastModified().toString()

    check(oldSum != newFileSum) {
        println("Skip EnvironmentProperty recreation")
        return
    }

    with(targetFile) {
        println("Begin Create EnvironmentProperty")

        val protoFile = bufferedReader().useLines {
            val props = it
                .filter { it.contains("=") }
                .map { it.split("=") }
                .map { it.first() to it[1] }

            protoFile(props)
        }.let {
            "// SUM: $newFileSum\n$it"
        }

        targetFile.writeText(protoFile)

        println("Finish create EnvironmentProperty: build/generated/envprop/kotlin/EnvironmentProperties.kt")
    }
}

private fun protoFile(props: Sequence<Pair<String, String>>): String {
    val proto =
        """
            object EnvironmentProperties {
                ${props.joinToString("\n                ") { "val ${it.first} = \"${it.second}\"" }}
            }
        """.trimIndent()

    return proto
}