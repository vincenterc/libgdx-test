package com.vincenterc.libgdxtest.mvc.controller

import com.vincenterc.libgdxtest.mvc.model.Arena
import com.vincenterc.libgdxtest.mvc.model.Droid
import com.vincenterc.libgdxtest.mvc.view.SimpleArenaRenderer

class GameEngine {

    private val droid = Droid().apply {
        x = (Arena.WIDTH / 2).toFloat()
        y = (Arena.HEIGHT / 2).toFloat()
    }
    private val arena = Arena(droid)

    private val renderer = SimpleArenaRenderer(arena)
    private val controller = ArenaController(arena)

    fun handleTouchUp(x: Int, y: Int): Boolean {
        return controller.onClick(x, y)
    }

    fun update(delta: Float) {
        controller.update(delta)
    }

    fun render() {
        renderer.render()
    }
}
