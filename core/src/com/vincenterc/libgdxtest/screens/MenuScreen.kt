package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.vincenterc.libgdxtest.TestGame

class MenuScreen(var game: TestGame) : BaseScreen() {

    override fun show() {
        super.show()

        val defaultScreenButton = TextButton("Default Screen", game.skin)
        defaultScreenButton.setPosition(
            stage.width / 2f - defaultScreenButton.width / 2f,
            stage.height / 2f - defaultScreenButton.height / 2f
        )
        defaultScreenButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                game.screen = DefaultScreen(game)
                return true
            }
        })
        stage.addActor(defaultScreenButton)

        val twoViewportsScreenButton = TextButton("Two Viewports Screen", game.skin)
        twoViewportsScreenButton.setPosition(
            stage.width / 2f - twoViewportsScreenButton.width / 2f,
            stage.height / 2f - twoViewportsScreenButton.height / 2f - 30f
        )
        twoViewportsScreenButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                game.screen = TwoViewportsScreen(game)
                return true
            }
        })
        stage.addActor(twoViewportsScreenButton)
    }
}