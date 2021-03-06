package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.vincenterc.libgdxtest.AssetDescriptors
import com.vincenterc.libgdxtest.TestGame

class AssetsLoadingScreen(game: TestGame) : BaseScreen(game) {

    private val img = Texture("sc_map.png")
    private var imgDrawWidth = img.width.toFloat()
    private var imgDrawHeight = img.height.toFloat()
    private var imgScale = 1f

    init {
        val imgRatio = img.width.toFloat() / img.height.toFloat()
        val worldRatio = stage.viewport.worldWidth / stage.viewport.worldHeight
        imgScale = if (imgRatio > worldRatio) {
            stage.viewport.worldWidth / img.width
        } else {
            stage.viewport.worldHeight / img.height
        }
        imgDrawWidth = img.width * imgScale
        imgDrawHeight = img.height * imgScale

        val menuButton = getMenuButton()
        stage.addActor(menuButton)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.batch.begin()
        stage.batch.draw(
            img,
            stage.viewport.worldWidth / 2f - imgDrawWidth / 2f,
            stage.viewport.worldHeight / 2f - imgDrawHeight / 2f,
            imgDrawWidth,
            imgDrawHeight
        )
        stage.batch.end()

        stage.act()
        stage.draw()
    }

    override fun hide() {
        super.hide()

        game.assets.manager.unload(AssetDescriptors.scMap.fileName)
    }
}