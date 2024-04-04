package com.pavlovalexey.torpedo.repository

import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Resource
import com.pavlovalexey.torpedo.model.Scene

interface GameRepository {
    fun getInitialDialogue(): Dialogue
    fun getDialogueByIndex(index: Int): Dialogue?
    fun getInitialScene(): Scene
    fun getResource(): Resource
}