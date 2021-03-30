/*
 * Jan Kampherbeek, (c)  2021.
 * Enigma Charts is open source.
 * Please check the file copyright.txt in the root of the source for further details.
 */

package com.radixpro.enigma.charts.ui

import com.radixpro.enigma.charts.shared.Dictionary
import com.radixpro.enigma.libfe.fxbuilders.*

import javafx.scene.control.ButtonBar

import javafx.scene.Scene

import com.radixpro.enigma.libfe.texts.Rosetta.getText
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox

import javafx.scene.layout.Pane
import javafx.stage.Modality
import javafx.stage.Stage


class AboutScreen {

    private val height = 420.0
    private val heightOfCenter = 250.0
    private val width = 620.0
    private lateinit var version: String

    private val stage = Stage()

    fun show(version: String) {
        this.version = version
        stage.initModality(Modality.APPLICATION_MODAL)
        stage.title = getText("screenabout.title")
        stage.scene = Scene(defineGridPane())
        stage.show()
    }

    private fun defineGridPane(): GridPane {
        val grid = GridPaneBuilder()
            .setHGap(Dictionary.GAP)
            .setPrefWidth(width)
            .setPrefHeight(height)
            .setPadding(Insets(Dictionary.GAP))
            .setStyleSheet(Dictionary.STYLESHEET)
            .build()
        grid.add(defineTitlePane(), 0, 0, 1, 1)
        grid.add(PaneBuilder().setPrefHeight(48.0).build(), 0, 1, 1, 1)
        grid.add(defineMainHBox(), 0, 2, 1, 1)
        grid.add(PaneBuilder().setPrefHeight(12.0).build(), 0, 3, 1, 1)
        grid.add(defineButtonBar(), 0, 4, 1, 1)
        return grid

    }

    private fun defineTitlePane(): Pane {
        return PaneBuilder()
            .setPrefWidth(width)
            .setPrefHeight(Dictionary.TITLE_HEIGHT)
            .setStyleClass(Dictionary.STYLE_TITLE_PANE)
            .setChildren(arrayListOf(
                LabelBuilder()
                    .setText(getText("screenabout.title") + " " + version)
                    .setPrefWidth(width)
                    .setStyleClass(Dictionary.STYLE_TITLE_TEXT)
                    .build()))
            .build()
    }

    private fun defineMainHBox(): HBox {
        return HBoxBuilder().setPrefWidth(width).setPrefHeight(heightOfCenter)
            .setChildren(arrayListOf(defineImage(), defineMainText())).build()
    }

    private fun defineMainText(): Label {
        return LabelBuilder().setText(getText("screenabout.maintext")).build()
    }

    private fun defineButtonBar(): ButtonBar {
        val buttonBar = ButtonBar()
        val btnExit: Button = ButtonBuilder().setText(getText("shared.btn_close")).build()
        btnExit.onAction = EventHandler { stage.close() }
        buttonBar.buttons.add(btnExit)
        return buttonBar
    }

    private fun defineImage(): ImageView {
        val image = Image("img/ziggurat.png")
        val imageView = ImageView(image)
        imageView.fitWidth = 223.0
        imageView.fitHeight = 86.0
        imageView.isPickOnBounds = true
        imageView.isPreserveRatio = true
        return imageView
    }

}