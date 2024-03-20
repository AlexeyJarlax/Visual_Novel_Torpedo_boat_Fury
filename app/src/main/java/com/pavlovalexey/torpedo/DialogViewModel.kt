package com.pavlovalexey.torpedo

import com.pavlovalexey.torpedo.data.Dialog

class DialogViewModel(private val dialogs: List<Dialog>) {
    private var currentDialogIndex = 0

    fun getNextDialog(): Dialog? {
        if (currentDialogIndex < dialogs.size) {
            val nextDialog = dialogs[currentDialogIndex]
            currentDialogIndex++
            return nextDialog
        }
        return null
    }
}