package com.vincenterc.libgdxtest.platformer.controller

import com.vincenterc.libgdxtest.platformer.Bob
import com.vincenterc.libgdxtest.platformer.World


class WorldController(private val world: World) {

    enum class Keys {
        LEFT, RIGHT, JUMP, FIRE
    }

    private val bob = world.bob

    companion object {
        var keys = HashMap<Keys, Boolean>()

        init {
            keys[Keys.LEFT] = false
            keys[Keys.RIGHT] = false
            keys[Keys.JUMP] = false
            keys[Keys.FIRE] = false
        }
    }

    // Key presses and touches
    fun leftPressed() {
        keys[Keys.LEFT] = true
    }

    fun rightPressed() {
        keys[Keys.RIGHT] = true
    }

    fun jumpPressed() {
        keys[Keys.JUMP] = true
    }

    fun firePressed() {
        keys[Keys.FIRE] = false
    }

    fun leftReleased() {
        keys[Keys.LEFT] = false
    }

    fun rightReleased() {
        keys[Keys.RIGHT] = false
    }

    fun jumpReleased() {
        keys[Keys.JUMP] = false
    }

    fun fireReleased() {
        keys[Keys.FIRE] = false
    }

    // The main update method
    fun update(delta: Float) {
        processInput()
        bob.update(delta)
    }

    // Change Bob's state and parameters based on input controls
    private fun processInput() {
        if (keys[Keys.LEFT]!!) {
            // left is pressed
            bob.facingLeft = true
            bob.state = Bob.State.WALKING
            bob.velocity.x = -Bob.SPEED
        }

        if (keys[Keys.RIGHT]!!) {
            // left is pressed
            bob.facingLeft = false
            bob.state = Bob.State.WALKING
            bob.velocity.x = Bob.SPEED
        }

        // need to check if both or none direction are pressed, then Bob is idle
        if (keys[Keys.LEFT]!! && keys[Keys.RIGHT]!! ||
            !keys[Keys.LEFT]!! && !keys[Keys.RIGHT]!!
        ) {
            bob.state = Bob.State.IDLE
            // acceleration is 0 on the x
            bob.acceleration.x = 0f
            // horizontal speed is 0
            bob.velocity.x = 0f
        }
    }
}