package com.vincenterc.libgdxtest.mvc.model

import com.vincenterc.libgdxtest.Config
import kotlin.random.Random

class Arena(val droid: Droid) {

    companion object {
        const val WIDTH = Config.screenWidth / 32
        const val HEIGHT = Config.screenHeight / 32
    }

    val grid = Array(HEIGHT) { y ->
        Array<Any?>(WIDTH) { x ->
            if (y == droid.y.toInt() && x == droid.x.toInt()) droid else null
        }
    }
    val obstacles = List(5) {
        var x = Random.nextInt(WIDTH)
        var y = Random.nextInt(HEIGHT)
        while (grid[y][x] != null) {
            x = Random.nextInt(WIDTH)
            y = Random.nextInt(HEIGHT)
        }
        grid[y][x] = Obstacle(x.toFloat(), y.toFloat())
        grid[y][x] as Obstacle
    }
    val enemies = List(5) {
        var x = Random.nextInt(WIDTH)
        var y = Random.nextInt(HEIGHT)
        while (grid[y][x] != null) {
            x = Random.nextInt(WIDTH)
            y = Random.nextInt(HEIGHT)
        }
        grid[y][x] = Enemy(x.toFloat(), y.toFloat())
        grid[y][x] as Enemy
    }
}