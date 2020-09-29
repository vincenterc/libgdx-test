package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.vincenterc.libgdxtest.TestGame

class MenuScreen(var game: TestGame) : ScreenAdapter() {
    var stage = Stage(ScreenViewport())

    override fun show() {
        Gdx.input.inputProcessor = stage

        var defaultScreenButton = TextButton("Default Screen", game.skin, "default")
        defaultScreenButton.setPosition(
            Gdx.graphics.width / 2f - defaultScreenButton.width / 2f,
            Gdx.graphics.height / 2f
        )
        defaultScreenButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                game.screen = DefaultScreen(game)
                return true
            }
        })
        stage.addActor(defaultScreenButton)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(Color.LIGHT_GRAY.r, Color.LIGHT_GRAY.g, Color.LIGHT_GRAY.b, Color.LIGHT_GRAY.a)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act()
        stage.draw()
    }

    override fun hide() {
        Gdx.input.inputProcessor = null
    }
}