package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.vincenterc.libgdxtest.TestGame

class MenuScreen(var game: TestGame) : BaseScreen() {

    override fun show() {
        super.show()

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

        val table = Table()
        table.defaults().fillX().spaceBottom(10f)
        table.add(testScreenButton)
        table.row()
        table.add(twoViewportsScreenButton)
        table.row()
        table.add(cameraControlScreenButton)
        table.setFillParent(true)
//        table.debug = true
        stage.addActor(table)
    }
}