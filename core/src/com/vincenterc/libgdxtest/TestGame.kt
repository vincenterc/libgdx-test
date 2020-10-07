package com.vincenterc.libgdxtest

import com.badlogic.gdx.Game
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.vincenterc.libgdxtest.screens.SplashScreen

class TestGame : Game() {

    lateinit var skin: Skin
    lateinit var assets: Assets

    override fun create() {
        assets = Assets()
        assets.load()
        setScreen(SplashScreen(this))
    }

    override fun dispose() {
        super.dispose()

        skin.dispose()
        assets.dispose()
    }
}