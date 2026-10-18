import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
    base
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.javafx) apply false
    alias(libs.plugins.ktlint)
}

allprojects {
    group = "dev.hawkx.distributionworkbench"
    version = "0.1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

ktlint {
    filter {
        exclude("**/build/**", "private/**", "src/**")
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    val moduleName = name

    extensions.configure<KotlinJvmProjectExtension> {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of(25))
            vendor.set(JvmVendorSpec.ADOPTIUM)
        }
        compilerOptions {
            allWarningsAsErrors.set(true)
        }
        sourceSets {
            named("main") {
                kotlin.setSrcDirs(listOf("kotlin"))
            }
            named("test") {
                kotlin.setSrcDirs(listOf(rootProject.file("tests/$moduleName/kotlin")))
            }
        }
    }

    extensions.configure<SourceSetContainer> {
        named("main") {
            resources.setSrcDirs(listOf("resources"))
        }
        named("test") {
            resources.setSrcDirs(listOf(rootProject.file("tests/$moduleName/resources")))
        }
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}

tasks.named("assemble") {
    dependsOn(subprojects.map { "${it.path}:assemble" })
}

tasks.named("check") {
    dependsOn("ktlintCheck")
    dependsOn(subprojects.map { "${it.path}:check" })
}
