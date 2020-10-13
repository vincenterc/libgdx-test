package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.vincenterc.libgdxtest.AssetDescriptors
import com.vincenterc.libgdxtest.Config
import com.vincenterc.libgdxtest.TestGame

class MenuScreen(game: TestGame) : BaseScreen(game) {

    init {
        val testScreenButton = TextButton("Test", game.skin)
        testScreenButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                game.screen = TestScreen(game)
                return true
            }
        })
        stage.addActor(testScreenButton)

        val twoViewportsScreenButton = TextButton("Two Viewports", game.skin)
        twoViewportsScreenButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                game.screen = TwoViewportsScreen(game)
                return true
            }
        })
        stage.addActor(twoViewportsScreenButton)

        val cameraControlScreenButton = TextButton("Camera Control", game.skin)
        cameraControlScreenButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                game.screen = CameraControlScreen(game)
                return true
            }
        })
        stage.addActor(cameraControlScreenButton)

        val assetsLoadingScreenButton = TextButton("Assets Loading", game.skin)
        assetsLoadingScreenButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                game.assets.manager.load(AssetDescriptors.scMap)
                game.screen = LoadingScreen(game, AssetsLoadingScreen::class)
                return true
            }
        })
        stage.addActor(assetsLoadingScreenButton)

        val table = Table()
        table.defaults().fillX().spaceBottom(10f)
        table.add(testScreenButton)
        table.row()
        table.add(twoViewportsScreenButton)
        table.row()
        table.add(cameraControlScreenButton)
        table.row()
        table.add(assetsLoadingScreenButton)
        table.setFillParent(true)
        if (Config.debug) table.debug = true
        stage.addActor(table)
    }
}