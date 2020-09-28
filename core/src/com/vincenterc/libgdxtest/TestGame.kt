package com.vincenterc.libgdxtest

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.vincenterc.libgdxtest.screens.DefaultScreen

class TestGame : Game() {

    lateinit var batch: SpriteBatch

    override fun create() {
        batch = SpriteBatch()

        setScreen(DefaultScreen(this))
    }

    override fun dispose() {
        batch.dispose()
    }
}