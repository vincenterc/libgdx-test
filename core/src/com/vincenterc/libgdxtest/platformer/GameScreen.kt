package com.vincenterc.libgdxtest.platformer

import com.badlogic.gdx.*
import com.badlogic.gdx.graphics.GL20
import com.vincenterc.libgdxtest.platformer.controller.WorldController

class GameScreen : ScreenAdapter(), InputProcessor {

    private lateinit var world: World
    private lateinit var renderer: WorldRenderer
    private lateinit var controller: WorldController

    private var width = 0
    private var height = 0

    override fun show() {
        world = World()
        renderer = WorldRenderer(world, false)
        controller = WorldController(world)

        Gdx.input.inputProcessor = this
    }

    override fun resize(width: Int, height: Int) {
        renderer.setSize(width, height)

        this.width = width
        this.height = height
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        controller.update(delta)
        renderer.render()
    }

    override fun hide() {
        Gdx.input.inputProcessor = null
    }

    override fun dispose() {
        Gdx.input.inputProcessor = null
    }

    // InputProcessor methods
    override fun keyDown(keycode: Int): Boolean {
        if (keycode == Input.Keys.LEFT) controller.leftPressed()
        if (keycode == Input.Keys.RIGHT) controller.rightPressed()
        if (keycode == Input.Keys.Z) controller.jumpPressed()
        if (keycode == Input.Keys.X) controller.firePressed()

        return true
    }

    override fun keyUp(keycode: Int): Boolean {
        if (keycode == Input.Keys.LEFT) controller.leftReleased()
        if (keycode == Input.Keys.RIGHT) controller.rightReleased()
        if (keycode == Input.Keys.Z) controller.jumpReleased()
        if (keycode == Input.Keys.X) controller.fireReleased()

        return true
    }

    override fun keyTyped(character: Char): Boolean {
        return false
    }

    override fun touchDown(x: Int, y: Int, pointer: Int, button: Int): Boolean {
        // if (Gdx.app.type != Application.ApplicationType.Android) return false

        if (x < width / 2 && y > height / 2) {
            controller.leftPressed()
        }
        if (x > width / 2 && y > height / 2) {
            controller.rightPressed()
        }

        return true
    }

    override fun touchUp(x: Int, y: Int, pointer: Int, button: Int): Boolean {
        // if (Gdx.app.type != Application.ApplicationType.Android) return false

        if (x < width / 2 && y > height / 2) {
            controller.leftReleased()
        }
        if (x > width / 2 && y > height / 2) {
            controller.rightReleased()
        }

        return true
    }

    override fun touchDragged(x: Int, y: Int, pointer: Int): Boolean {
        return false
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false
    }

    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        return false
    }
}