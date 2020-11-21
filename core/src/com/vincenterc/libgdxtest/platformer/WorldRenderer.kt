package com.vincenterc.libgdxtest.platformer

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Array

class WorldRenderer(private val world: World, var debug: Boolean) {

    companion object {
        private const val CAMERA_WIDTH = 10f
        private const val CAMERA_HEIGHT = 7f
    }

    private var cam: OrthographicCamera

    // for debug rendering
    private val debugRenderer = ShapeRenderer()

    // Textures
    private lateinit var bobTexture: Texture
    private lateinit var blockTexture: Texture

    private val spriteBatch = SpriteBatch()
    private var width = 0
    private var height = 0
    private var ppuX = 0.0f
    private var ppuY = 0.0f

    init {
        cam = OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT)
        cam.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0f)
        cam.update()
        loadTextures()
    }

    fun setSize(w: Int, h: Int) {
        width = w
        height = h
        ppuX = width.toFloat() / CAMERA_WIDTH
        ppuY = height.toFloat() / CAMERA_HEIGHT
    }

    private fun loadTextures() {
        bobTexture = Texture(Gdx.files.internal("platformer/bob_01.png"))
        blockTexture = Texture(Gdx.files.internal("platformer/block.png"))
    }

    fun render() {
        spriteBatch.begin()
        drawBlocks()
        drawBob()
        spriteBatch.end()

        if (debug) drawDebug()
    }

    private fun drawBlocks() {
        for (block in Array.ArrayIterator(world.blocks)) {
            spriteBatch.draw(
                blockTexture,
                block.position.x * ppuX,
                block.position.y * ppuY,
                Block.SIZE * ppuX,
                Block.SIZE * ppuY
            )
        }
    }

    private fun drawBob() {
        val bob = world.bob
        spriteBatch.draw(
            bobTexture,
            bob.position.x * ppuX,
            bob.position.y * ppuY,
            Bob.SIZE * ppuX,
            Bob.SIZE * ppuY
        )
    }

    private fun drawDebug() {
        debugRenderer.projectionMatrix = cam.combined

        // render blocks
        debugRenderer.begin(ShapeRenderer.ShapeType.Line)
        for (block in Array.ArrayIterator(world.blocks)) {
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