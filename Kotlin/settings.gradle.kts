plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

val parentDir = rootDir.parentFile

if (parentDir != null) {
    rootProject.name = "${parentDir.name}-${rootDir.name}"
} else {
    rootProject.name = rootDir.name
}