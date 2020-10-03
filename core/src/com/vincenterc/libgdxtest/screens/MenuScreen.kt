package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.vincenterc.libgdxtest.TestGame

class MenuScreen(var game: TestGame) : BaseScreen() {

    override fun show() {
        super.show()

        val testScreenButton = TextButton("Test Screen", game.skin)
        testScreenButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                game.screen = TestScreen(game)
                return true
            }
        })
        stage.addActor(testScreenButton)

        val twoViewportsScreenButton = TextButton("Two Viewports Screen", game.skin)
        twoViewportsScreenButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                game.screen = TwoViewportsScreen(game)
                return true
            }
        })
        stage.addActor(twoViewportsScreenButton)

        val table = Table()
        table.defaults().fillX()
        table.add(testScreenButton).spaceBottom(10f)
        table.row()
        table.add(twoViewportsScreenButton)
        table.setFillParent(true)
//        table.debug = true
        stage.addActor(table)
    }
}