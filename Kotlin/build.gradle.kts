import org.gradle.internal.os.OperatingSystem

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

task<Exec>("sampleJudge") {
    dependsOn("build")

    commandLine(
        "oj", "t", "-d", "tests",
        "-c", "java -jar build/libs/${project.name}-$version.jar"
    )
}

task<Exec>("forceSubmit") {
    commandLine(accCommand, "submit", "-s", "--", "-y")
}

task("submit") {
    dependsOn("sampleJudge", "forceSubmit")

    tasks.named("forceSubmit").configure { mustRunAfter("sampleJudge") }
}

task("s") {
    dependsOn("submit")
}
