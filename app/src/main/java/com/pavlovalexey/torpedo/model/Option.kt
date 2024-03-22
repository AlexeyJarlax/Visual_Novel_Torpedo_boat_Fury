package com.pavlovalexey.torpedo.model

data class Option(
    val text: String,
    val nextDialogueIndex: Int,
    val resourceEffect: Resource
)