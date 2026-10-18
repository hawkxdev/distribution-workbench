pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "distribution-workbench"

include("domain", "application", "infrastructure", "desktop")

listOf("domain", "application", "infrastructure", "desktop").forEach { module ->
    project(":$module").projectDir = file("src/$module")
}
