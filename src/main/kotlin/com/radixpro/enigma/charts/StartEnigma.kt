package com.radixpro.enigma.charts

import com.radixpro.enigma.charts.di.Injector
import com.radixpro.enigma.libfe.texts.Rosetta
import javafx.application.Application
import javafx.stage.Stage


/**
 * Starts the application.
 */
class StartEnigma: Application() {

    fun run(args: Array<String?>) {
        launch(*args)
    }

    override fun start(primaryStage: Stage) {
        Injector.injectStartScreen().show()
    }

}