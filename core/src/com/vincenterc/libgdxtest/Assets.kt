package com.vincenterc.libgdxtest

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.ui.Skin

class Assets {

    var manager = AssetManager()

    fun load() {
        manager.load(AssetDescriptors.skin)
    }

    fun dispose() {
        manager.dispose()
    }
}

object AssetDescriptors {
    val skin = AssetDescriptor<Skin>("skin/uiskin.json", Skin::class.java)
    val scMap = AssetDescriptor<Texture>("sc_map.png", Texture::class.java)
}

