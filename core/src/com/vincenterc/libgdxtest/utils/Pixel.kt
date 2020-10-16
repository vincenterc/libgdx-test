package com.vincenterc.libgdxtest.utils

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap

class Pixel {
    companion object {
        fun createSingleWhitePixel(): Pixmap {
            val pixmap = Pixmap(1, 1, Pixmap.Format.RGBA8888)
            pixmap.setColor(Color.WHITE)
            pixmap.drawPixel(0, 0)

            return pixmap
        }
    }
}