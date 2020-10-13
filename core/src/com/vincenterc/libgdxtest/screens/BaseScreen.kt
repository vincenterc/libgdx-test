package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.viewport.FitViewport
import com.vincenterc.libgdxtest.Config
import com.vincenterc.libgdxtest.TestGame

open class BaseScreen(protected val game: TestGame) : ScreenAdapter() {
    protected val stage = Stage(FitViewport(Config.screenWidth.toFloat(), Config.screenHeight.toFloat()))

    override fun show() {
        Gdx.input.inputProcessor = stage
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(Color.LIGHT_GRAY.r, Color.LIGHT_GRAY.g, Color.LIGHT_GRAY.b, Color.LIGHT_GRAY.a)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act(delta)
        stage.draw()
    }

    override fun hide() {
        Gdx.input.inputProcessor = null

        stage.dispose()
    }

    protected fun getMenuButton(): TextButton {
        val menuButton = TextButton("Menu", game.skin)
        menuButton.setPosition(
            stage.width - menuButton.width - 10f,
            stage.height - menuButton.height - 10f
        )
        menuButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                game.screen = MenuScreen(game)
                return true
            }
        })

        return menuButton
    }
}