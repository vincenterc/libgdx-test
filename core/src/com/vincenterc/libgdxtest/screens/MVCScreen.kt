package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.GL20
import com.vincenterc.libgdxtest.Config
import com.vincenterc.libgdxtest.TestGame
import com.vincenterc.libgdxtest.mvc.controller.GameEngine

class MVCScreen(game: TestGame) : BaseScreen(game), InputProcessor {

    private val engine = GameEngine()

    override fun show() {
        Gdx.input.inputProcessor = this
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        engine.update(delta)
        engine.render()
    }

    override fun hide() {
        Gdx.input.inputProcessor = null
    }

    override fun dispose() {
        Gdx.input.inputProcessor = null
    }

    // InputProcessor
    override fun keyDown(keycode: Int): Boolean {
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        return false
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return engine.handleTouchUp(screenX, Config.screenHeight - screenY)
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        return false
    }
}