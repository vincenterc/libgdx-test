package com.vincenterc.libgdxtest.mvc.controller

import com.vincenterc.libgdxtest.mvc.model.Arena


class ArenaController(private val arena: Arena) {

    companion object {
        const val unit = 32
    }

    private var targetX = 0f
    private var targetY = 0f
    private var moving = false

    fun update(delta: Float) {
        val droid = arena.droid

        if (moving) {
            // move on X
            var bearing = if (droid.x > targetX) -1 else 1
            if (droid.x != targetX) {
                droid.x = droid.x + bearing * droid.speed * delta
                // check if arrived
                if (droid.x < targetX && bearing == -1
                    || droid.x > targetX && bearing == 1
                ) droid.x = targetX
            }

            // move on Y
            bearing = if (droid.y > targetY) -1 else 1
            if (droid.y != targetY) {
                droid.y = droid.y + bearing * droid.speed * delta
                // check if arrived
                if (droid.y < targetY && bearing == -1
                    || droid.y > targetY && bearing == 1
                ) droid.y = targetY
            }

            // check if arrived
            if (droid.x == targetX && droid.y == targetY) moving = false
        }
    }

    fun onClick(x: Int, y: Int): Boolean {
        targetX = (x / unit).toFloat()
        targetY = (y / unit).toFloat()
        if (arena.grid[targetY.toInt()][targetX.toInt()] == null) {
            // start moving the droid towards the target
            moving = true
            return true
        }
        return false
    }
}