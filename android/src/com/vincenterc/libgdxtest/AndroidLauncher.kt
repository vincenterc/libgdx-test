package com.vincenterc.libgdxtest

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration

class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        config.useAccelerometer = Config.useAccelerometer
        config.useCompass = Config.useCompass
        config.useGyroscope = Config.useGyroscope

        initialize(TestGame(), config)
    }
}