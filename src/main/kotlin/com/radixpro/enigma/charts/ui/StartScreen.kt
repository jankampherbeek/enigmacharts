package com.radixpro.enigma.charts.ui

import com.radixpro.enigma.charts.shared.Dictionary.GAP
import com.radixpro.enigma.charts.shared.Dictionary.STYLESHEET
import com.radixpro.enigma.charts.shared.Dictionary.STYLE_SUBTITLE_PANE
import com.radixpro.enigma.charts.shared.Dictionary.STYLE_SUBTITLE_TEXT
import com.radixpro.enigma.charts.shared.Dictionary.STYLE_TITLE_PANE
import com.radixpro.enigma.charts.shared.Dictionary.STYLE_TITLE_TEXT
import com.radixpro.enigma.charts.shared.Dictionary.SUBTITLE_HEIGHT
import com.radixpro.enigma.charts.shared.Dictionary.TITLE_HEIGHT
import com.radixpro.enigma.charts.shared.Help
import com.radixpro.enigma.libfe.fxbuilders.*
import com.radixpro.enigma.libfe.texts.Rosetta
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.stage.Modality
import javafx.stage.Stage

class StartScreen {
    private val height = 600.0
    private val width = 800.0
    private lateinit var subtitleChartsPane: Pane
    private lateinit var subtitleConfigsPane: Pane
    private lateinit var txtTitle: String
    private lateinit var btnRemoveChart: Button
    private lateinit var btnNewChart: Button
    private lateinit var btnSearchChart: Button
    private lateinit var btnSaveChart: Button
    private lateinit var btnWheel: Button
    private lateinit var btnPositions: Button
    private lateinit var btnAnalysis: Button
    private lateinit var btnProgressive: Button
    private lateinit var btnManageConfigs: Button
    private lateinit var btnHelp: Button
    private lateinit var btnExit: Button
    private lateinit var menuBar: MenuBar
    private lateinit var miNewChart: MenuItem
    private lateinit var miSearchChart: MenuItem
    private lateinit var miSaveChart: MenuItem
    private lateinit var miDeleteChart: MenuItem
    private lateinit var miSettings: MenuItem
    private lateinit var miManageConfig: MenuItem
    private lateinit var miWheel: MenuItem
    private lateinit var miPositions: MenuItem
    private lateinit var miAspects: MenuItem
    private lateinit var miMidpoints: MenuItem
    private lateinit var miHarmonics: MenuItem
    private lateinit var miDecanates: MenuItem
    private lateinit var miTerms: MenuItem
    private lateinit var miDodecatemoria: MenuItem
    private lateinit var miTransits: MenuItem
    private lateinit var miSecundary: MenuItem
    private lateinit var miPrimary: MenuItem
    private lateinit var miSolar: MenuItem
    private lateinit var miManual: MenuItem
    private lateinit var miAbout: MenuItem
    private var statusComplete = false

    private lateinit var stage: Stage

    fun show() {
        initialize()
        stage = Stage()
        stage.minHeight = height
        stage.minWidth = width
        stage.initModality(Modality.APPLICATION_MODAL)
        stage.title = txtTitle
        stage.scene = Scene(createGridPane())
        stage.show()
    }

    private fun initialize() {
        txtTitle = Rosetta.getText("screenstart.title")
        defineButtons()
        menuBar = createMenuBar()
        defineListeners()
        subtitleChartsPane = defineSubTitlePane(Rosetta.getText("screenstart.subtitlecharts"))
        subtitleConfigsPane = defineSubTitlePane(Rosetta.getText("screenstart.subtitleconfigs"))
        checkStatus()
    }

    private fun createGridPane(): GridPane {
        val grid = GridPaneBuilder()
            .setHGap(GAP)
            .setPrefWidth(width)
            .setPrefHeight(height)
            .setPadding(Insets(GAP))
            .setStyleSheet(STYLESHEET)
            .build()
        grid.add(menuBar, 0, 0, 1, 1)
        grid.add(defineTitlePane(), 0, 1, 1, 1)
        grid.add(subtitleChartsPane, 0, 2, 1, 1)
        grid.add(defineListViewCharts(), 0, 3, 1, 1)
        grid.add(PaneBuilder().setPrefHeight(6.0).build(), 0, 4, 1, 1)
        grid.add(defineBtnBarChartActions(), 0, 5, 1, 1)
        grid.add(PaneBuilder().setPrefHeight(24.0).build(), 0, 6, 1, 1)
        grid.add(subtitleConfigsPane, 0, 7, 1, 1)
        grid.add(defineListViewConfigs(), 0, 8, 1, 1)
        grid.add(PaneBuilder().setPrefHeight(6.0).build(), 0, 9, 1, 1)
        grid.add(defineBtnBarConfig(), 0, 10, 1, 1)
        grid.add(PaneBuilder().setPrefHeight(48.0).build(), 0, 11, 1, 1)
        grid.add(defineBtnBarGeneral(), 0, 12, 1, 1)
        return grid
    }

    private fun defineTitlePane(): Pane {
        return PaneBuilder()
            .setPrefWidth(width)
            .setPrefHeight(TITLE_HEIGHT)
            .setStyleClass(STYLE_TITLE_PANE)
            .setChildren(arrayListOf(
                LabelBuilder()
                .setText(Rosetta.getText("screenstart.title"))
                .setPrefWidth(width)
                .setStyleClass(STYLE_TITLE_TEXT)
                .build()))
            .build()
    }

    private fun defineSubTitlePane(subTitle: String): Pane {
        return PaneBuilder()
            .setPrefWidth(width)
            .setPrefHeight(SUBTITLE_HEIGHT)
            .setStyleClass(STYLE_SUBTITLE_PANE)
            .setChildren(arrayListOf(
                LabelBuilder()
                    .setText(subTitle)
                    .setPrefWidth(width)
                    .setStyleClass(STYLE_SUBTITLE_TEXT)
                    .build()))
            .build()
    }

    private fun defineListViewCharts(): ListView<String> {
        val listView = ListViewBuilder().setPrefHeight(180.0).setPrefWidth(width).build()
        return listView
    }

    private fun defineListViewConfigs(): ListView<String> {
        val listView = ListViewBuilder().setPrefHeight(120.0).setPrefWidth(width).build()
        return listView
    }

    private fun defineButtons() {
        btnRemoveChart = ButtonBuilder().setText(Rosetta.getText("shared.btn_remove")).build()
        btnNewChart = ButtonBuilder().setText(Rosetta.getText("shared.btn_new")).build()
        btnSearchChart = ButtonBuilder().setText(Rosetta.getText("shared.btn_search")).build()
        btnSaveChart = ButtonBuilder().setText(Rosetta.getText("shared.btn_save")).build()
        btnWheel = ButtonBuilder().setText(Rosetta.getText("screenstart.btn_wheel")).build()
        btnPositions = ButtonBuilder().setText(Rosetta.getText("screenstart.btn_positions")).build()
        btnAnalysis = ButtonBuilder().setText(Rosetta.getText("screenstart.btn_analysis")).build()
        btnProgressive = ButtonBuilder().setText(Rosetta.getText("screenstart.btn_progressive")).build()
        btnManageConfigs = ButtonBuilder().setText(Rosetta.getText("screenstart.btn_manageconfigs")).build()
        btnHelp = ButtonBuilder().setText(Rosetta.getText("shared.btn_help")).build()
        btnExit = ButtonBuilder().setText(Rosetta.getText("shared.btn_exit")).build()
    }

    private fun defineListeners() {
        btnRemoveChart.onAction = EventHandler { onRemoveChart()}
        btnNewChart.onAction = EventHandler { onNewChart() }
        btnSaveChart.onAction = EventHandler { onSaveChart() }
        btnSearchChart.onAction = EventHandler { onSearchChart() }
        btnWheel.onAction = EventHandler { onWheel() }
        btnPositions.onAction = EventHandler { onPositions() }
        btnAnalysis.onAction = EventHandler { onAnalysis() }
        btnProgressive.onAction = EventHandler { onProgressive() }
        btnManageConfigs.onAction = EventHandler { onManageConfigs() }
        btnHelp.onAction = EventHandler { onHelp() }
        btnExit.onAction = EventHandler { onExit() }
        miNewChart.onAction = EventHandler { onNewChart() }
        miSearchChart.onAction = EventHandler { onSearchChart() }
        miSaveChart.onAction = EventHandler { onSaveChart() }
        miDeleteChart.onAction = EventHandler { onDeleteChart() }
        miSettings.onAction = EventHandler { onSettings() }
        miManageConfig.onAction = EventHandler { onManageConfigs() }
        miWheel.onAction = EventHandler { onWheel() }
        miPositions.onAction = EventHandler { onPositions() }
        miManual.onAction = EventHandler { onManual() }
        miAbout.onAction = EventHandler { onAbout() }
        // TODO menuitems for analysis and for progression
    }

    private fun defineBtnBarChartActions(): ButtonBar {
        return ButtonBarBuilder().setPrefWidth(width)
            .setButtons(arrayListOf(btnWheel, btnPositions, btnAnalysis, btnProgressive, btnRemoveChart, btnNewChart,
                btnSearchChart, btnSaveChart)).build()
    }

    private fun defineBtnBarConfig(): ButtonBar {
        return ButtonBarBuilder().setPrefWidth(width)
            .setButtons(arrayListOf(btnManageConfigs)).build()
    }

    private fun defineBtnBarGeneral(): ButtonBar {
        return ButtonBarBuilder().setPrefWidth(width)
            .setButtons(arrayListOf(btnHelp, btnExit)).build()
    }

    private fun createMenuBar(): MenuBar {
        val menuCharts = Menu(Rosetta.getText("menustart.chart"))
        val menuEdit = Menu(Rosetta.getText("menustart.edit"))
        val menuShow = Menu(Rosetta.getText("menustart.show"))
        val menuAnalysis = Menu(Rosetta.getText("menustart.analysis"))
        val menuDivisions = Menu(Rosetta.getText("menustart.analysis.division"))
        val menuProgressive = Menu(Rosetta.getText("menustart.progressive"))
        val menuHelp = Menu(Rosetta.getText("menustart.help"))
        miNewChart = MenuItem(Rosetta.getText("menustart.chart.new"))
        miSearchChart = MenuItem(Rosetta.getText("menustart.chart.search"))
        miSaveChart = MenuItem(Rosetta.getText("menustart.chart.save"))
        miDeleteChart = MenuItem(Rosetta.getText("menustart.chart.delete"))
        menuCharts.items.addAll(miNewChart, miSearchChart, miSaveChart, miDeleteChart)
        miSettings = MenuItem(Rosetta.getText("menustart.edit.settings"))
        miManageConfig = MenuItem(Rosetta.getText("menustart.edit.manageconfig"))
        menuEdit.items.addAll(miSettings, miManageConfig)
        miWheel = MenuItem(Rosetta.getText("menustart.show.wheel"))
        miPositions = MenuItem(Rosetta.getText("menustart.show.positions"))
        menuShow.items.addAll(miWheel, miPositions)
        miAspects = MenuItem(Rosetta.getText("menustart.analysis.aspects"))
        miMidpoints = MenuItem(Rosetta.getText("menustart.analysis.midpoints"))
        miHarmonics = MenuItem(Rosetta.getText("menustart.analysis.harmonics"))
        miDecanates = MenuItem(Rosetta.getText("menustart.analysis.division.decanates"))
        miTerms = MenuItem(Rosetta.getText("menustart.analysis.division.terms"))
        miDodecatemoria = MenuItem(Rosetta.getText("menustart.analysis.division.dodecatemoria"))
        menuDivisions.items.addAll(miDecanates, miTerms, miDodecatemoria)
        menuAnalysis.items.addAll(miAspects, miMidpoints, miHarmonics, menuDivisions)
        miTransits = MenuItem(Rosetta.getText("menustart.progressive.transits"))
        miSecundary = MenuItem(Rosetta.getText("menustart.progressive.secundary"))
        miPrimary = MenuItem(Rosetta.getText("menustart.progressive.primary"))
        miSolar = MenuItem(Rosetta.getText("menustart.progressive.solar"))
        menuProgressive.items.addAll(miTransits, miSecundary, miPrimary, miSolar)
        miManual = MenuItem(Rosetta.getText("menustart.help.manual"))
        miAbout = MenuItem(Rosetta.getText("menustart.help.about"))
        menuHelp.items.addAll(miManual, miAbout)
        return MenuBar(menuCharts, menuEdit, menuShow, menuAnalysis, menuProgressive, menuHelp)
    }

    private fun checkStatus() {
        disEnableBtn(btnRemoveChart, statusComplete)
        disEnableBtn(btnSaveChart, statusComplete)
        disEnableBtn(btnWheel, statusComplete)
        disEnableBtn(btnPositions, statusComplete)
        disEnableBtn(btnAnalysis, statusComplete)
        disEnableBtn(btnProgressive, statusComplete)

        disEnableMenuItem(miSaveChart, statusComplete)
        disEnableMenuItem(miDeleteChart, statusComplete)
        disEnableMenuItem(miWheel, statusComplete)
        disEnableMenuItem(miPositions, statusComplete)
        disEnableMenuItem(miAspects, statusComplete)
        disEnableMenuItem(miMidpoints, statusComplete)
        disEnableMenuItem(miHarmonics, statusComplete)
        disEnableMenuItem(miDecanates, statusComplete)
        disEnableMenuItem(miTerms, statusComplete)
        disEnableMenuItem(miDodecatemoria, statusComplete)
        disEnableMenuItem(miTransits, statusComplete)
        disEnableMenuItem(miSecundary, statusComplete)
        disEnableMenuItem(miPrimary, statusComplete)
        disEnableMenuItem(miSolar, statusComplete)
    }

    private fun disEnableBtn(btn: Button, enabled: Boolean) {
        btn.isDisable = !enabled
        btn.isFocusTraversable = enabled
    }

    private fun disEnableMenuItem(item: MenuItem, enabled: Boolean) {
        item.isDisable = !enabled
    }

    private fun onSaveChart() {

    }


    private fun onRemoveChart() {

    }

    private fun onDeleteChart() {

    }

    private fun onNewChart() {

    }

    private fun onSearchChart() {

    }

    private fun onWheel() {

    }

    private fun onPositions() {

    }

    private fun onAnalysis() {

    }

    private fun onProgressive() {

    }

    private fun onManageConfigs() {
        println("Manage configs.....")
    }

    private fun onSettings() {

    }

    private fun onManual() {

    }

    private fun onAbout() {
        
    }

    private fun onHelp() {
        Help(Rosetta.getHelpText("help.screenstart.title"),
            Rosetta.getHelpText("help.screenstart.content")).showContent()
    }

    private fun onExit() {

    }
}