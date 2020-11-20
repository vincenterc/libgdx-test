package com.vincenterc.libgdxtest.platformer

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20

class GameScreen : ScreenAdapter() {

    private lateinit var world: World
    private lateinit var renderer: WorldRenderer

    override fun show() {
        world = World()
        renderer = WorldRenderer(world)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        renderer.render()
    }
}