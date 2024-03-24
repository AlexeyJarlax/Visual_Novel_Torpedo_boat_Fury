package com.pavlovalexey.torpedo.model

data class Option(
    val text: String,
    val nextDialogueIndex: Int,
    val resourceEffect: Resource,
    val NotUsingThisCaseIfCapitalIsTrue: Boolean = false // сценарий Карл Маркс
)