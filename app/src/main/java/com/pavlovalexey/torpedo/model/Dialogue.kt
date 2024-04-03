package com.pavlovalexey.torpedo.model

data class Dialogue(
    var text: String? = null, // необязательный параметр на случай использования кнопок с ответом без вопроса
    val scene: Scene,
    val options: List<Option>,
    val dialogFunction: (() -> Unit)? = null // Функция, принимающая () -> Unit и возвращающая Unit, по умолчанию null
)