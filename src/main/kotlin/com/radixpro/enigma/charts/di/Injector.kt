package com.radixpro.enigma.charts.di

import com.radixpro.enigma.charts.ui.StartScreen

object Injector {

    fun injectStartScreen(): StartScreen {
        return StartScreen()
    }

}