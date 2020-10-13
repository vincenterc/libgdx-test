package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.vincenterc.libgdxtest.AssetDescriptors
import com.vincenterc.libgdxtest.TestGame

class SplashScreen(game: TestGame) : BaseScreen(game) {

    private val font = BitmapFont()
    private val label: Label

    private var timePassed = 0f

    init {
        val labelStyle = Label.LabelStyle()
        labelStyle.font = font
        labelStyle.fontColor = Color.LIGHT_GRAY
        label = Label("Loading...", labelStyle)
    }

    override fun show() {
        label.setFontScale(2f)
        label.setPosition(
            stage.viewport.worldWidth / 2f - label.width * label.fontScaleX / 2f,
            stage.viewport.worldHeight / 4f
        )
        stage.addActor(label)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        timePassed += delta

        if (game.assets.manager.update()) {
            if (hasPassed(1f)) {
                game.skin = game.assets.manager.get(AssetDescriptors.skin)
                game.screen = MenuScreen(game)
            }
        }

        stage.act()
        stage.draw()
    }

    override fun dispose() {
        super.dispose()

        font.dispose()
    }

    private fun hasPassed(sec: Float): Boolean {
        return timePassed > sec
    }
}