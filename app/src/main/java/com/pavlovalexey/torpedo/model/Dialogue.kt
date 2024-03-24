package com.pavlovalexey.torpedo.model

data class Dialogue(
    val text: String,
    val scene: Scene,
    val options: List<Option>,
    val capital: Boolean = false // сценарий Карл Маркс
)