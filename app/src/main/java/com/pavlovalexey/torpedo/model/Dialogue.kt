package com.pavlovalexey.torpedo.model

/** основной конструкт игры. Диалоги последовательно погружают игрока в игровой мир. Некоторые содержат опции для выбора*/

data class Dialogue(
    var text: String? = null,
    var scene: Scene? = null,
    val musicId: Int? = null,
    var options: List<Option>,
    var dialogFunction: (() -> Unit)? = null // Функция, принимающая () -> Unit и возвращающая Unit, по умолчанию null
)