package com.pavlovalexey.torpedo.model

data class Option(
    val text: String,
    val nextDialogueIndex: Int,
    val resourceEffect: Resource? = null // модификатор ресурсов (не обязательный элемент опции)
)