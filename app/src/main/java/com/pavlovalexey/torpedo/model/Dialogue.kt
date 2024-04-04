package com.pavlovalexey.torpedo.model

data class Dialogue(
    var text: String? = null, // необязательный параметр на случай использования кнопок с ответом без вопроса
    var scene: Scene? = null,
    val musicId: Int? = null,
    var options: List<Option>,
    var dialogFunction: (() -> Unit)? = null // Функция, принимающая () -> Unit и возвращающая Unit, по умолчанию null
)