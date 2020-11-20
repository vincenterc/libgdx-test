package com.vincenterc.libgdxtest.screens

import com.vincenterc.libgdxtest.TestGame
import com.vincenterc.libgdxtest.platformer.GameScreen

class PlatformerScreen(game: TestGame) : BaseScreen(game) {

    override fun show() {
        super.show()

        game.screen = GameScreen()
    }
}