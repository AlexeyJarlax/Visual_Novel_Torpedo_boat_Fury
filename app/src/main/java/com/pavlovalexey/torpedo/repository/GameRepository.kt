package com.pavlovalexey.torpedo.repository

import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Scene

interface GameRepository {
    fun getInitialScene(): Scene
    fun getSceneByDialogueIndex(dialogueIndex: Int): Scene
}