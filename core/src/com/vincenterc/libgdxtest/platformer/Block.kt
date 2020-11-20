package com.vincenterc.libgdxtest.platformer

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

class Block(var position: Vector2) {

    companion object {
        const val SIZE = 1f
    }

    var bounds = Rectangle()

    init {
        bounds.width = SIZE
        bounds.height = SIZE
    }
}