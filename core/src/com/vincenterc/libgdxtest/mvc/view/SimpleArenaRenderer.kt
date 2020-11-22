package com.vincenterc.libgdxtest.mvc.view

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.vincenterc.libgdxtest.mvc.model.Arena

class SimpleArenaRenderer(private val arena: Arena) : Renderer {

    private val shapeRenderer = ShapeRenderer()

    override fun render() {
        val cellSize = 32

        // render the grid
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        shapeRenderer.color = Color(0f, 0.5f, 0f, 0.75f)
        for (i in 0..Arena.WIDTH) {
            shapeRenderer.line(
                (i * cellSize).toFloat(), 0F,
                (i * cellSize).toFloat(), (Arena.HEIGHT * cellSize).toFloat()
            )
        }
        for (i in 0..Arena.HEIGHT) {
            shapeRenderer.line(
                0F, (i * cellSize).toFloat(),
                (Arena.WIDTH * cellSize).toFloat(), (i * cellSize).toFloat()
            )
        }
        shapeRenderer.end()

        // render the obstacles
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.color = Color(0f, 0f, 1f, 1f)
        for (obs in arena.obstacles) {
            val x = obs.x * cellSize + 2
            val y = obs.y * cellSize + 2
            shapeRenderer.rect(x, y, cellSize - 4f, cellSize - 4f)
        }
        shapeRenderer.end()

        // render the enemies
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.color = Color(1f, 0f, 0f, 1f)
        for (enemy in arena.enemies) {
            val x = enemy.x * cellSize + 2
            val y = enemy.y * cellSize + 2
            shapeRenderer.ellipse(x, y, cellSize - 4f, cellSize - 4f)
        }
        shapeRenderer.end()

        // render player droid
        val droid = arena.droid
        val x = droid.x * cellSize
        val y = droid.y * cellSize
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.color = Color(0f, 1f, 0f, 1f)
        shapeRenderer.ellipse(x + 2, y + 2, cellSize - 4f, cellSize - 4f)
        shapeRenderer.color = Color(0.7f, 0.5f, 0f, 1f)
        shapeRenderer.rect(x + 10, y + 10, cellSize - 20f, cellSize - 20f)
        shapeRenderer.end()
    }
}