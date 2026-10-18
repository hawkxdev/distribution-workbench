/** Desktop window integration. */
package dev.hawkx.distributionworkbench.desktop

import javafx.application.Application
import javafx.application.Platform
import javafx.scene.control.Label
import javafx.stage.Stage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.awt.image.BufferedImage
import java.nio.file.Files
import java.nio.file.Path
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit
import javax.imageio.ImageIO

/** Verify desktop composition. */
class DesktopWindowTest {
    @Test
    fun opensConfiguredWindowWithPackagedViewAndStyles() {
        val result = CompletableFuture<Unit>()
        Platform.startup { Platform.setImplicitExit(false) }
        Platform.runLater {
            runCatching { verifyWindow() }
                .onSuccess { result.complete(Unit) }
                .onFailure { result.completeExceptionally(it) }
        }
        try {
            result.get(30, TimeUnit.SECONDS)
        } finally {
            Platform.exit()
        }
    }

    /** Inspect visible window. */
    private fun verifyWindow() {
        assertTrue(Application::class.java.module.isNamed)
        val application = WorkbenchApplication()
        val stage = Stage()
        try {
            application.start(stage)
            stage.scene.root.applyCss()
            stage.scene.root.layout()
            assertTrue(stage.isShowing)
            assertEquals("Distribution Workbench", stage.title)
            val heading = stage.scene.lookup(".workspace-title") as Label
            assertEquals(stage.title, heading.text)
            assertEquals(1, stage.scene.root.stylesheets.size)
            saveRequestedScreenshot(stage)
        } finally {
            stage.close()
            application.stop()
        }
    }

    /** Capture requested evidence. */
    private fun saveRequestedScreenshot(stage: Stage) {
        val destination = System.getenv("WORKBENCH_SCREENSHOT") ?: return
        val screenshot = stage.scene.snapshot(null)
        val width = screenshot.width.toInt()
        val height = screenshot.height.toInt()
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        val reader = screenshot.pixelReader
        for (y in 0 until height) {
            for (x in 0 until width) {
                image.setRGB(x, y, reader.getArgb(x, y))
            }
        }
        val target = Path.of(destination)
        Files.createDirectories(target.toAbsolutePath().parent)
        check(ImageIO.write(image, "png", target.toFile())) { "No PNG image writer available" }
    }
}
