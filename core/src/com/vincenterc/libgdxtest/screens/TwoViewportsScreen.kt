package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.vincenterc.libgdxtest.Config
import com.vincenterc.libgdxtest.TestGame

class TwoViewportsScreen(game: TestGame) : BaseScreen(game) {

    private val viewport = StretchViewport(Config.screenWidth.toFloat(), Config.screenHeight.toFloat())
    private val batch = SpriteBatch()
    private val img = Texture(Gdx.files.internal("badlogic.jpg"))

    init {
        // Center camera
        viewport.camera.position.set(viewport.worldWidth / 2f, viewport.worldHeight / 2f, 0f)

        val menuButton = getMenuButton()
        stage.addActor(menuButton)
    }

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)

        viewport.update(width, height)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        viewport.apply()
        batch.projectionMatrix = viewport.camera.combined
        batch.begin()
        batch.draw(img, 0f, 0f)
        batch.end()

        stage.viewport.apply()
        stage.act()
        stage.draw()
    }

    override fun hide() {
        super.hide()

        batch.dispose()
        img.dispose()
    }
}