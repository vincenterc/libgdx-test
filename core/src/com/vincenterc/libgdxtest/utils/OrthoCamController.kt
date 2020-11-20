package com.vincenterc.libgdxtest.utils

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector3

class OrthoCamController(
    private val camera: OrthographicCamera,
    var worldWidth: Float,
    var worldHeight: Float,
    var scrollScale: Float = 0.05f
) : InputAdapter() {

    private var currentPos = Vector3()
    private var lastPos = Vector3(-1f, -1f, -1f)
    private var deltaOfPos = Vector3()

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        camera.unproject(currentPos.set(screenX.toFloat(), screenY.toFloat(), 0f))
        if (lastPos != Vector3(-1f, -1f, -1f)) {
            camera.unproject(lastPos)
            deltaOfPos = lastPos.sub(currentPos)
            camera.position.add(deltaOfPos)
            clampCameraPosition()
        }
        lastPos.set(screenX.toFloat(), screenY.toFloat(), 0f)

        return true
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        lastPos.set(-1f, -1f, -1f)

        return true
    }

    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        camera.zoom += amountY * scrollScale
        clampCameraPosition()

        return true
    }

    private fun clampCameraPosition() {
        val worldRatio = worldWidth / worldHeight
        val viewportRatio = camera.viewportWidth / camera.viewportHeight

        camera.zoom = if (worldRatio > viewportRatio) {
            MathUtils.clamp(camera.zoom, 0.1f, worldHeight / camera.viewportHeight)
        } else {
            MathUtils.clamp(camera.zoom, 0.1f, worldWidth / camera.viewportWidth)
        }

        val effectiveViewportWidth = camera.viewportWidth * camera.zoom
        val effectiveViewportHeight = camera.viewportHeight * camera.zoom

        camera.position.x =
            MathUtils.clamp(camera.position.x, effectiveViewportWidth / 2f, worldWidth - effectiveViewportWidth / 2f)
        camera.position.y =
            MathUtils.clamp(camera.position.y, effectiveViewportHeight / 2f, worldHeight - effectiveViewportHeight / 2f)
    }
}