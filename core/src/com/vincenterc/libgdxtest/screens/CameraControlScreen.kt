package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.viewport.FitViewport
import com.vincenterc.libgdxtest.OrthoCamController
import com.vincenterc.libgdxtest.TestGame

class CameraControlScreen(var game: TestGame) : BaseScreen() {

    var viewport = FitViewport(640f, 480f)
    var batch = SpriteBatch()
    private var img = Texture(Gdx.files.internal("map-large.jpg"))

    override fun show() {
        viewport.camera.position.set(viewport.worldWidth / 2f, viewport.worldHeight / 2f, 0f)

        var cameraController = OrthoCamController(viewport.camera as OrthographicCamera)
        var inputMultiplexer = InputMultiplexer()
        inputMultiplexer.addProcessor(stage)
        inputMultiplexer.addProcessor(cameraController)
        Gdx.input.inputProcessor = inputMultiplexer

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
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
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