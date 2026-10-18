/** Desktop application composition. */
package dev.hawkx.distributionworkbench.desktop

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import org.koin.dsl.koinApplication
import org.koin.dsl.module

/** Compose desktop dependencies. */
class WorkbenchApplication : Application() {
    private val dependencies = koinApplication {
        modules(
            module {
                single { DesktopConfiguration.load() }
            },
        )
    }

    /** Show application window. */
    override fun start(stage: Stage) {
        val configuration = dependencies.koin.get<DesktopConfiguration>()
        val view = checkNotNull(javaClass.getResource(configuration.viewResource)) {
            "Missing desktop view resource: ${configuration.viewResource}"
        }
        val root = FXMLLoader(view).load<Parent>()
        stage.title = configuration.title
        stage.scene = Scene(root, configuration.width, configuration.height)
        stage.show()
        System.getLogger(javaClass.name).log(System.Logger.Level.INFO, "Desktop window opened: {0}", stage.title)
    }

    /** Release application dependencies. */
    override fun stop() {
        dependencies.close()
    }
}
