package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.vincenterc.libgdxtest.TestGame

class DefaultScreen(var game: TestGame) : ScreenAdapter() {

    private var stage = Stage(ScreenViewport())
    private lateinit var img: Texture

    override fun show() {
        Gdx.input.inputProcessor = stage

        var menuButton = TextButton("Menu", game.skin, "default")
        menuButton.setPosition(
            Gdx.graphics.width - menuButton.width - 10f,
            Gdx.graphics.height - menuButton.height - 10f
        )
        menuButton.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                game.screen = MenuScreen(game)
                return true
            }
        })
        stage.addActor(menuButton)

        img = Texture("badlogic.jpg")
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        game.batch.begin()
        game.batch.draw(img, 0f, 0f)
        game.batch.end()

        stage.act()
        stage.draw()
    }

    override fun hide() {
        img.dispose()
    }
}