package com.pavlovalexey.torpedo.repository

import androidx.lifecycle.LiveData
import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Resource
import com.pavlovalexey.torpedo.model.Scene

interface GameRepository {
    fun getInitialDialogue(): Dialogue
    fun getDialogueByIndex(index: Int): Dialogue?
    fun getInitialScene(): Scene
    fun getResource(): Resource
    fun updateResources(resourceEffect: Resource) // сохраняем после ресурсЭффект
    fun getNextBookFragment(): String
    fun updateDialogueWithNextFragment(dialogue: Dialogue, nextFragment: String)
    fun saveResources(resources: Resource)
    fun loadResources(): Resource
    fun loadCurrentDialogueIndex(): Int
    fun setCurrentDialogueIndex(index: Int)
    fun setCurrentScene(scene: Scene)
}