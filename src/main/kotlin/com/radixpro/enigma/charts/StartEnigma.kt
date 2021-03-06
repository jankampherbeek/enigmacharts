/*
 * Jan Kampherbeek, (c)  2021.
 * Enigma Charts is open source.
 * Please check the file copyright.txt in the root of the source for further details
 */
package com.radixpro.enigma.charts

import com.radixpro.enigma.charts.di.Injector
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