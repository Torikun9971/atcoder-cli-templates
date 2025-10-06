plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

// set project name
val parentDir = rootDir.parentFile

if (parentDir != null) {
    rootProject.name = "${parentDir.name}-${rootDir.name}"
} else {
    rootProject.name = rootDir.name
}