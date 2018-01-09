package io.github.benjohnde.ankeader

import javafx.application.Application
import javafx.application.Platform
import javafx.stage.FileChooser
import javafx.stage.Stage

class MinimalisticUI : Application() {

    @Throws(Exception::class)
    override fun start(primaryStage: Stage) {
        val fileChooser = FileChooser()
        fileChooser.title = "Open Resource File"
        val retValue = fileChooser.showOpenDialog(primaryStage)
        println(retValue)
        Platform.exit()
    }
}
