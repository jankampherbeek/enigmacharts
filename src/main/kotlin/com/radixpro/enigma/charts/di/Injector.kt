/*
 * Jan Kampherbeek, (c)  2021.
 * Enigma Charts is open source.
 * Please check the file copyright.txt in the root of the source for further details
 */
package com.radixpro.enigma.charts.di

import com.radixpro.enigma.charts.ui.StartScreen

object Injector {

    fun injectStartScreen(): StartScreen {
        return StartScreen()
    }

}