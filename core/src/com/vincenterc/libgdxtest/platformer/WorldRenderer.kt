package com.vincenterc.libgdxtest.platformer

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class WorldRenderer(private val world: World) {

    private val cam = OrthographicCamera(10f, 7f)

    // for debug rendering
    private val debugRenderer = ShapeRenderer()

    init {
        cam.position.set(5f, 3.5f, 0f)
        cam.update()
    }

    fun render() {
        debugRenderer.projectionMatrix = cam.combined

        // render blocks
        debugRenderer.begin(ShapeRenderer.ShapeType.Line)
        for (block in world.blocks) {
            val rect = block.bounds
            val x1 = block.position.x + rect.x
            val y1 = block.position.y + rect.y
            debugRenderer.color = Color(1f, 0f, 0f, 1f)
            debugRenderer.rect(x1, y1, rect.width, rect.height)
        }

        // render Bob
        val bob = world.bob
        val rect = bob.bounds
        val x1 = bob.position.x + rect.x
        val y1 = bob.position.y + rect.y
        debugRenderer.color = Color(0f, 1f, 0f, 1f)
        debugRenderer.rect(x1, y1, rect.width, rect.height)

        debugRenderer.end()
    }
}