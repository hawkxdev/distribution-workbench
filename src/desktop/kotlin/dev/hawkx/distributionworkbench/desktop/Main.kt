/** Desktop launcher entry. */
package dev.hawkx.distributionworkbench.desktop

import javafx.application.Application

/** Launch desktop application. */
fun main(args: Array<String>) {
    Application.launch(WorkbenchApplication::class.java, *args)
}
