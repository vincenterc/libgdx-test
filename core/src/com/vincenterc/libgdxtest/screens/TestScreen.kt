package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.vincenterc.libgdxtest.TestGame

class TestScreen(game: TestGame) : BaseScreen(game) {

    private var img = Texture(Gdx.files.internal("badlogic.jpg"))

    override fun show() {
        super.show()

        val menuButton = getMenuButton()
        stage.addActor(menuButton)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.batch.begin()
        stage.batch.draw(img, 0f, 0f)
        stage.batch.end()

        stage.act()
        stage.draw()
    }

    override fun hide() {
        super.hide()

        img.dispose()
    }
}