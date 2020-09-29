package com.vincenterc.libgdxtest

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.vincenterc.libgdxtest.screens.MenuScreen

class TestGame : Game() {

    lateinit var batch: SpriteBatch
    lateinit var skin:Skin

    override fun create() {
        batch = SpriteBatch()
        skin = Skin(Gdx.files.internal("skin/uiskin.json"))

        setScreen(MenuScreen(this))
    }

    override fun dispose() {
        batch.dispose()
    }
}