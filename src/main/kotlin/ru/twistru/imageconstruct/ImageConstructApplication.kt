package ru.twistru.imageconstruct

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage
import org.opencv.core.Core

class ImageConstructor : Application() {
    override fun start(stage: Stage) {
        val mainScene = Scene(MainActivity(), 1280.0, 800.0)
        stage.title = "ImageConstructor"
        stage.scene = mainScene
        stage.show()
    }
}

fun main() {
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME)
    Application.launch(ImageConstructor::class.java)
}