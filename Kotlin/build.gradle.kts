import org.gradle.internal.os.OperatingSystem
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.register
import java.nio.file.Path
import kotlin.io.path.Path

plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = ""
version = "1.0.0"

val accCommand = if (OperatingSystem.current().isWindows) "acc.cmd" else "acc"
val accConfigDir = accConfigDir()

repositories {
    mavenCentral()

    flatDir {
        dirs(accConfigDir.resolve("atcoder-libs"))
    }
}

dependencies {
    optionalImplementation(libs.ac.library.java) {
        atcoderLib("ac_library17").exists()
    }

    implementation(libs.kotlinx.collections.immutable)
}

kotlin {
    val java_version = libs.versions.java.get().toInt()

    jvmToolchain(java_version)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }

    from(
        configurations.runtimeClasspath.get()
            .filter { it.name.endsWith(".jar") }
            .map { zipTree(it) }
    )

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks.register<Exec>("sampleJudge") {
    dependsOn("build")

    commandLine("oj",
        "t",
        "-d", "tests",
        "-c", "java -jar build/libs/${project.name}-$version.jar"
    )
}

tasks.register<Exec>("forceSubmit") {
    commandLine(accCommand,
        "submit",
        "-s",
        "--",
        "-y"
    )
}

tasks.register("submit") {
    dependsOn("sampleJudge")
    finalizedBy("forceSubmit")
}

tasks.register("s") {
    dependsOn("submit")
}

fun accConfigDir(): Path {
    val process = try {
        ProcessBuilder(accCommand, "config-dir")
            .start()
    } catch (e: Exception) {
        throw GradleException("Failed to execute '$accCommand config-dir': ${e.message}")
    }

    val path = process.inputReader().use { it.readLine() }
    val exitCode = process.waitFor()

    process.destroyForcibly()
    if (exitCode != 0) {
        throw GradleException("Failed to obtain AtCoder CLI config directory.")
    }

    return Path(path)
}

fun atcoderLib(name: String): File {
    return accConfigDir
        .resolve("atcoder-libs")
        .resolve("$name.jar")
        .toFile()
}

fun DependencyHandlerScope.optionalImplementation(
    dependencyNotation: Provider<MinimalExternalModuleDependency>,
    condition: () -> Boolean
): Dependency? {
    val name = dependencyNotation.get().name

    return if (condition()) {
        logger.lifecycle("Adding optional dependency: $name")
        implementation(dependencyNotation)
    } else {
        logger.lifecycle("Skipping optional dependency: $name (not found)")
        null
    }
}