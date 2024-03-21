package com.pavlovalexey.torpedo.presentation

import com.pavlovalexey.torpedo.data.Dialog
import com.pavlovalexey.torpedo.domain.DialogNavigator

class DialogViewModel(
    private val dialogs: List<Dialog>,
    private val dialogNavigator: DialogNavigator
) {
    private var currentDialogIndex = 0

    fun getNextDialog(): Dialog? {
        if (currentDialogIndex < dialogs.size) {
            val dialog = dialogs[currentDialogIndex]
            currentDialogIndex++
            return dialog
        }
        return null
    }

    fun processChoice(option: String): Dialog? {
        // Проверяем, есть ли у текущего диалога варианты выбора
        val currentDialog = dialogs[currentDialogIndex - 1]
        if (currentDialog.choices != null && currentDialog.choices.contains(option)) {
            // Находим следующий диалог, соответствующий выбору пользователя
            for (dialog in dialogs.subList(currentDialogIndex, dialogs.size)) {
                if (dialog.characterName == currentDialog.characterName) {
                    // Найден следующий диалог для того же персонажа, возвращаем его
                    currentDialogIndex = dialogs.indexOf(dialog) + 1
                    return dialog
                }
            }
        }
        // В случае, если выбор не влияет на дальнейший ход сюжета, возвращаем null
        return null
    }
}