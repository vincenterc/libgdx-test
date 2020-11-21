package com.vincenterc.libgdxtest.platformer

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

class Bob(var position: Vector2) {

    enum class State {
        IDLE, WALKING, JUMPING, DYING
    }

    companion object {
        const val SPEED = 4f // unit per second
        const val JUMP_VELOCITY = 1f
        const val SIZE = 0.5f // half a unit
    }

    var acceleration = Vector2()
    var velocity = Vector2()
    var bounds = Rectangle()
    var state = State.IDLE
    var facingLeft = true

    init {
        bounds.width = SIZE
        bounds.height = SIZE
    }

    fun update(delta: Float) {
        position.add(velocity.cpy().scl(delta))
    }
}