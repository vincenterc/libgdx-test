package com.vincenterc.libgdxtest.platformer

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array

class World {

    // The blocks making up the world
    var blocks = Array<Block>()

    // Our player controlled hero
    lateinit var bob: Bob

    init {
        createDemoWorld()
    }

    private fun createDemoWorld() {
        bob = Bob(Vector2(7f, 2f))

        for (i in 0..9) {
            blocks.add(Block(Vector2(i.toFloat(), 0f)))
            blocks.add(Block(Vector2(i.toFloat(), 6f)))
            if (i > 2) blocks.add(Block(Vector2(i.toFloat(), 1f)))
        }

        blocks.add(Block(Vector2(9f, 2f)))
        blocks.add(Block(Vector2(9f, 3f)))
        blocks.add(Block(Vector2(9f, 4f)))
        blocks.add(Block(Vector2(9f, 5f)))

        blocks.add(Block(Vector2(6f, 3f)))
        blocks.add(Block(Vector2(6f, 4f)))
        blocks.add(Block(Vector2(6f, 5f)))
    }
}