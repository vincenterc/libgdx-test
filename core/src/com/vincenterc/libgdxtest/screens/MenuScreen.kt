package com.vincenterc.libgdxtest.screens

import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.vincenterc.libgdxtest.AssetDescriptors
import com.vincenterc.libgdxtest.Config
import com.vincenterc.libgdxtest.TestGame

class MenuScreen(game: TestGame) : BaseScreen(game) {

    private val menuButtons = listOf(
        MenuButton("Test", fun() { game.screen = TestScreen(game) }),
        MenuButton("Two Viewports", fun() { game.screen = TwoViewportsScreen(game) }),
        MenuButton("Camera Control", fun() { game.screen = CameraControlScreen(game) }),
        MenuButton("Assets Loading", fun() {
            game.assets.manager.load(AssetDescriptors.scMap)
            game.screen = LoadingScreen(game, AssetsLoadingScreen::class)
        })
    )

    init {
        generateMenuButtons()
    }

    private fun generateMenuButtons() {
        val table = Table()
        table.defaults().fillX().spaceBottom(10f)

        menuButtons.forEach {
            val button = TextButton(it.title, game.skin)
            button.addListener(object : InputListener() {
                override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                    it.touchDownFn.invoke()
                    return true
                }
            })
            table.add(button)
            table.row()
        }

        table.setFillParent(true)
        if (Config.debug) table.debug = true
        stage.addActor(table)
    }
}

data class MenuButton(val title: String, val touchDownFn: () -> Unit)