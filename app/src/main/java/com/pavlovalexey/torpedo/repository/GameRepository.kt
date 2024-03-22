package com.pavlovalexey.torpedo.repository

import com.pavlovalexey.torpedo.model.Dialogue

interface GameRepository {
    fun getInitialDialogue(): Dialogue
    fun getDialogueByIndex(index: Int): Dialogue?
}