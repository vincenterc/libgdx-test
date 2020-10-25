package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
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
    private var animationTimer = 0f
    private var animationTimer2 = 0f
    private var animation2Scale = 1f

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

        animationTimer += Gdx.graphics.deltaTime

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            animationTimer2 += Gdx.graphics.deltaTime
            animation2Scale = 1f
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            animationTimer2 += Gdx.graphics.deltaTime
            animation2Scale = -1f
        }

        val currentRunningAnimationFrame = runningAnimation.getKeyFrame(animationTimer, true)
        val currentRunningAnimation2Frame = runningAnimation.getKeyFrame(animationTimer2, true)

        stage.batch.begin()
        stage.batch.draw(
            currentRunningAnimationFrame,
            stage.viewport.worldWidth / 2f - currentRunningAnimationFrame.regionWidth / 2f,
            stage.viewport.worldHeight / 2f + currentRunningAnimationFrame.regionHeight / 4f
        )
        stage.batch.draw(
            currentRunningAnimation2Frame,
            stage.viewport.worldWidth / 2f - currentRunningAnimation2Frame.regionWidth / 2f,
            stage.viewport.worldHeight / 2f - currentRunningAnimation2Frame.regionHeight * 5 / 4f,
            currentRunningAnimation2Frame.regionWidth / 2f, currentRunningAnimation2Frame.regionHeight / 2f,
            currentRunningAnimation2Frame.regionWidth.toFloat(), currentRunningAnimation2Frame.regionHeight.toFloat(),
            animation2Scale, 1f, 0f
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