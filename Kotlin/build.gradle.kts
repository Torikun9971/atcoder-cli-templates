import org.gradle.internal.os.OperatingSystem
import org.gradle.kotlin.dsl.register

plugins {
    kotlin("jvm") version "1.8.20"
}

group = ""
version = "1.0.0"

val accCommand = if (OperatingSystem.current().isWindows) "acc.cmd" else "acc"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
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

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
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
