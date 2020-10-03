package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.vincenterc.libgdxtest.TestGame

class TestScreen(var game: TestGame) : BaseScreen() {

    private var viewport = StretchViewport(640f, 480f)
    private var batch = SpriteBatch()
    private var img = Texture(Gdx.files.internal("badlogic.jpg"))

    override fun show() {
        super.show()

        // Center camera
        viewport.camera.position.set(viewport.worldWidth / 2f, viewport.worldHeight / 2f, 0f)

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
        stage.addActor(menuButton)
    }

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)

        viewport.update(width, height)
    }

    override fun render(delta: Float) {
        super.render(delta)

        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        viewport.apply()
        batch.projectionMatrix = viewport.camera.combined
        batch.begin()
        batch.draw(img, 0f, 0f)
        batch.end()

        stage.viewport.apply()
        stage.draw()
    }

    override fun hide() {
        super.hide()

        img.dispose()
    }
}