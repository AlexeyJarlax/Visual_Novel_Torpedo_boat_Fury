package com.pavlovalexey.torpedo.presentation

import com.pavlovalexey.torpedo.data.Dialog

class DialogViewModel(
    private val dialogs: List<Dialog>
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
}