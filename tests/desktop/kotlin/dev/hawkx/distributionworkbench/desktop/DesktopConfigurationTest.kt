/** Desktop configuration checks. */
package dev.hawkx.distributionworkbench.desktop

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.util.Properties

/** Validate desktop settings. */
class DesktopConfigurationTest {
    @Test
    fun loadsPackagedConfiguration() {
        val configuration = DesktopConfiguration.load()
        assertEquals("Distribution Workbench", configuration.title)
        checkNotNull(javaClass.getResource(configuration.viewResource))
    }

    @Test
    fun rejectsMissingRequiredProperty() {
        val properties = validProperties().apply { remove("window.title") }
        assertThrows(IllegalArgumentException::class.java) {
            DesktopConfiguration.fromProperties(properties)
        }
    }

    @Test
    fun rejectsNonFiniteDimensions() {
        val properties = validProperties().apply { setProperty("window.width", "NaN") }
        assertThrows(IllegalArgumentException::class.java) {
            DesktopConfiguration.fromProperties(properties)
        }
    }

    @Test
    fun rejectsNegativeDimensions() {
        val properties = validProperties().apply { setProperty("window.height", "-1") }
        assertThrows(IllegalArgumentException::class.java) {
            DesktopConfiguration.fromProperties(properties)
        }
    }

    /** Create valid configuration. */
    private fun validProperties(): Properties = Properties().apply {
        setProperty("window.title", "Test workspace")
        setProperty("window.view", "/ui/workbench.fxml")
        setProperty("window.width", "800")
        setProperty("window.height", "600")
    }
}
