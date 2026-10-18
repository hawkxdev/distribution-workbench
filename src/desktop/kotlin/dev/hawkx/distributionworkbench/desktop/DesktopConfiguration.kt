/** Desktop configuration parsing. */
package dev.hawkx.distributionworkbench.desktop

import java.util.Properties

/** Validated window configuration. */
data class DesktopConfiguration(val title: String, val viewResource: String, val width: Double, val height: Double) {
    companion object {
        private const val DEFAULT_RESOURCE = "/desktop.properties"

        /** Load classpath configuration. */
        fun load(): DesktopConfiguration {
            val input = checkNotNull(DesktopConfiguration::class.java.getResourceAsStream(DEFAULT_RESOURCE)) {
                "Missing desktop configuration resource: $DEFAULT_RESOURCE"
            }
            val properties = input.use { Properties().apply { load(it) } }
            return fromProperties(properties)
        }

        /** Validate configuration properties. */
        internal fun fromProperties(properties: Properties): DesktopConfiguration = DesktopConfiguration(
            title = requiredText(properties, "window.title"),
            viewResource = requiredText(properties, "window.view"),
            width = positiveDimension(properties, "window.width"),
            height = positiveDimension(properties, "window.height"),
        )

        /** Read required text. */
        private fun requiredText(properties: Properties, key: String): String {
            val value = properties.getProperty(key)?.trim()
            require(!value.isNullOrEmpty()) { "Missing desktop configuration property: $key" }
            return value
        }

        /** Validate positive dimension. */
        private fun positiveDimension(properties: Properties, key: String): Double {
            val value = requiredText(properties, key).toDoubleOrNull()
            require(value != null && value.isFinite() && value > 0) {
                "Desktop configuration property must be a finite positive number: $key"
            }
            return value
        }
    }
}
