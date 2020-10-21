package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array
import com.vincenterc.libgdxtest.TestGame

class AnimationScreen(game: TestGame) : BaseScreen(game) {

    private val frameRows = 5
    private val frameCols = 6
    private val runningSheet = Texture("running.png")
    private val runningAnimation: Animation<TextureRegion>
    private var elapsedAnimationTime = 0f

    init {
        val runningSheetRegions = TextureRegion.split(
            runningSheet, runningSheet.width / frameCols, runningSheet.height / frameRows
        )
        val runningFrames = Array<TextureRegion>(frameCols * frameRows)
        runningSheetRegions.forEach { col ->
            col.forEach { runningFrames.add(it) }
        }
        runningAnimation = Animation(0.033f, runningFrames)

        val menuButton = getMenuButton()
        stage.addActor(menuButton)
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        elapsedAnimationTime += Gdx.graphics.deltaTime
        val currentRunningAnimationFrame = runningAnimation.getKeyFrame(elapsedAnimationTime, true)
        stage.batch.begin()
        stage.batch.draw(
            currentRunningAnimationFrame,
            stage.viewport.worldWidth / 2f - currentRunningAnimationFrame.regionWidth / 2f,
            stage.viewport.worldHeight / 2f - currentRunningAnimationFrame.regionHeight / 2f
        )
        stage.batch.end()

        stage.act()
        stage.draw()
    }

    override fun hide() {
        super.hide()

        runningSheet.dispose()
    }
}