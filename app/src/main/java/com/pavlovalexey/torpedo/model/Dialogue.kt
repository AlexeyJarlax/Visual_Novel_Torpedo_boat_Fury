package com.pavlovalexey.torpedo.model

data class Dialogue(
    var text: String,
    val scene: Scene,
    val options: List<Option>,
)