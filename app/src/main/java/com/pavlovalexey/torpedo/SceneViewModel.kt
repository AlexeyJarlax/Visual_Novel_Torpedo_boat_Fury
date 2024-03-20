package com.pavlovalexey.torpedo

import com.pavlovalexey.torpedo.data.Scene

class SceneViewModel(private val scene: Scene) {
    fun getScene(): Scene {
        return scene
    }
}