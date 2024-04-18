package com.pavlovalexey.torpedo.repository.dialogues

import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Scene
import com.pavlovalexey.torpedo.model.Scenes

object DialogueManager {

    val scenes: List<Scene> = Scenes.list

    val dialogues: MutableList<Pair<Int, Dialogue>> = mutableListOf()

    fun loadDialogues() {
        dialogues.clear() // Очищаем текущие диалоги перед загрузкой новых

        // В зависимости от индекса файла загружаем соответствующие диалоги
        val filenames = listOf("Dialogue00.kt", "Dialogue01.kt", /* добавьте остальные файлы */)
        for (filename in filenames) {
            val fileDialogues = when (filename) {
                "Dialogue00.kt" -> Dialogue00.dialogues
                "Dialogue01.kt" -> Dialogue01.dialogues
                // добавьте остальные файлы
                else -> emptyList()
            }
            dialogues.addAll(fileDialogues)
        }
    }

    fun getDialogues(scenes: List<Scene>): List<Pair<Int, Dialogue>> {
        var currentScene: Scene? = null // Инициализируем переменную для хранения текущей сцены
        return dialogues.mapNotNull { (index, dialogue) ->
            // Если у текущего диалога явно указана сцена, используем её
            val scene = dialogue.scene ?: currentScene
            currentScene = scene // Обновляем текущую сцену для следующего диалога
            scene?.let { // Проверяем, что сцена не null
                index to dialogue.copy(scene = it) // Возвращаем пару (индекс диалога, объект диалога с корректной сценой)
            }
        }
    }

    init {
        loadDialogues()
    }
}