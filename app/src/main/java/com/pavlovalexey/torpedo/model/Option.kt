package com.pavlovalexey.torpedo.model

/** опции для диалога с выбором вариантов.*/

data class Option(
    val text: String,
    val nextDialogueIndex: Int,
    val resourceEffect: Resource? = null, // модификатор ресурсов (не обязательный элемент опции)
    val optionFunction: (() -> Unit)? = null // Функция, принимающая () -> Unit и возвращающая Unit, по умолчанию null
)