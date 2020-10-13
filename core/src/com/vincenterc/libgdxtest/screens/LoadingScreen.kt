package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.vincenterc.libgdxtest.Config
import com.vincenterc.libgdxtest.TestGame
import kotlin.reflect.KClass

class LoadingScreen<T : Screen>(game: TestGame, private val nextScreen: KClass<T>) : BaseScreen(game) {

    private val progressBar = ProgressBar(0f, 100f, 1f, false, game.skin)

    init {
        progressBar.setAnimateDuration(1f)

        val table = Table()
        table.add(progressBar).width(stage.viewport.worldWidth / 3f)
        table.setFillParent(true)
        if (Config.debug) table.debug = true
        stage.addActor(table)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        game.assets.manager.update()
        progressBar.value = game.assets.manager.progress * 100
        if (game.assets.manager.isFinished && !progressBar.isAnimating) game.screen =
                // There maybe better ways to create instances
            nextScreen.constructors.first().call(game)

        stage.act()
        stage.draw()
    }
}