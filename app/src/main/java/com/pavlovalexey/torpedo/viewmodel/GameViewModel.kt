package com.pavlovalexey.torpedo.viewmodel

import androidx.lifecycle.ViewModel
import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Resource
import com.pavlovalexey.torpedo.repository.GameRepository

class GameViewModel(private val gameRepository: GameRepository) : ViewModel() {
    private var currentDialogueIndex = 0
    private var resources = Resource(0, 0, 0)

    private var currentDialogue: Dialogue = gameRepository.getInitialDialogue()

    fun getCurrentDialogue(): Dialogue {
        return currentDialogue
    }

    fun selectOption(optionIndex: Int) {
        val selectedOption = currentDialogue.options.getOrNull(optionIndex)
        selectedOption?.let { option ->
            currentDialogueIndex = option.nextDialogueIndex
            resources = Resource(
                resources.rubles + option.resourceEffect.rubles,
                resources.fame + option.resourceEffect.fame,
                resources.teamLoyalty + option.resourceEffect.teamLoyalty
            )
            currentDialogue = gameRepository.getDialogueByIndex(currentDialogueIndex)
                ?: throw IllegalStateException("No dialogue found for index: $currentDialogueIndex")
        }
    }

    // Функция для переключения на следующий диалог без выбора пользователя
    fun goToNextDialogue() {
        currentDialogueIndex++
        currentDialogue = gameRepository.getDialogueByIndex(currentDialogueIndex)
            ?: throw IllegalStateException("No dialogue found for index: $currentDialogueIndex")
    }
}