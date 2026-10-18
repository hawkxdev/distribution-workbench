import org.gradle.api.artifacts.component.ModuleComponentIdentifier
import org.gradle.process.CommandLineArgumentProvider

plugins {
    application
    alias(libs.plugins.javafx)
}

dependencies {
    implementation(project(":application"))
    implementation(project(":infrastructure"))
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.launcher)
}

javafx {
    version = libs.versions.javafx.get()
    modules("javafx.controls", "javafx.fxml")
}

application {
    mainClass.set("dev.hawkx.distributionworkbench.desktop.MainKt")
    applicationDefaultJvmArgs = listOf("--enable-native-access=javafx.graphics")
}

val javafxRuntime = configurations.named("runtimeClasspath").map { configuration ->
    configuration.incoming.artifactView {
        componentFilter { identifier ->
            identifier is ModuleComponentIdentifier && identifier.group == "org.openjfx"
        }
    }.files
}

tasks.withType<Test>().configureEach {
    classpath = classpath.minus(files(javafxRuntime))
    jvmArgumentProviders.add(
        CommandLineArgumentProvider {
            listOf(
                "--module-path",
                files(javafxRuntime).asPath,
                "--add-modules",
                "javafx.controls,javafx.fxml",
                "--enable-native-access=javafx.graphics",
            )
        },
    )
}
