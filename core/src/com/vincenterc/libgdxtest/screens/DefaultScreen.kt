package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.vincenterc.libgdxtest.TestGame

class DefaultScreen(var game: TestGame) : ScreenAdapter() {

    private lateinit var img: Texture

    override fun show() {
        img = Texture("badlogic.jpg")
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        game.batch.begin()
        game.batch.draw(img, 0f, 0f)
        game.batch.end()
    }

    override fun hide() {
        img.dispose()
    }
}