package com.vincenterc.libgdxtest.desktop

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.vincenterc.libgdxtest.Config
import com.vincenterc.libgdxtest.TestGame

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = Lwjgl3ApplicationConfiguration()
        config.setTitle("libGDX-test-game")
        config.setWindowedMode(Config.screenWidth, Config.screenHeight)

        Lwjgl3Application(TestGame(), config)
    }
}