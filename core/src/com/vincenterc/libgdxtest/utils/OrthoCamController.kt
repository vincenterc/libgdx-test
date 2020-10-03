package com.vincenterc.libgdxtest.utils

import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector3

class OrthoCamController(private val camera: OrthographicCamera) : InputAdapter() {

    private var currentPos = Vector3()
    private var lastPos = Vector3(-1f, -1f, -1f)
    private var deltaOfPos = Vector3()

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        camera.unproject(currentPos.set(screenX.toFloat(), screenY.toFloat(), 0f))
        if (lastPos != Vector3(-1f, -1f, -1f)) {
            camera.unproject(lastPos)
            deltaOfPos = lastPos.sub(currentPos)
            camera.position.add(deltaOfPos)
        }
        lastPos.set(screenX.toFloat(), screenY.toFloat(), 0f)

        return false
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        lastPos.set(-1f, -1f, -1f)

        return false
    }
}