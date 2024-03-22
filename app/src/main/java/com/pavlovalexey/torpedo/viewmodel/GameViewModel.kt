package com.pavlovalexey.torpedo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Resource
import com.pavlovalexey.torpedo.model.Scene
import com.pavlovalexey.torpedo.repository.GameRepository

class GameViewModel(private val gameRepository: GameRepository) : ViewModel() {
    private var currentDialogueIndex = 0
    private val _currentScene = MutableLiveData<Scene>()
    val currentScene: LiveData<Scene>
        get() = _currentScene

    private val _resources = MutableLiveData<Resource>()
    val resources: LiveData<Resource>
        get() = _resources

    init {
        _currentScene.value = gameRepository.getInitialScene()
        _resources.value = Resource(0, 0, 0)
    }

    fun selectOption(optionIndex: Int) {
        val selectedOption = currentDialogue.value?.options?.getOrNull(optionIndex)
        selectedOption?.let { option ->
            val nextDialogueIndex = option.nextDialogueIndex
            _resources.value = Resource(
                _resources.value?.rubles?.plus(option.resourceEffect.rubles) ?: 0,
                _resources.value?.fame?.plus(option.resourceEffect.fame) ?: 0,
                _resources.value?.teamLoyalty?.plus(option.resourceEffect.teamLoyalty) ?: 0
            )
            _currentScene.value = gameRepository.getSceneByDialogueIndex(nextDialogueIndex)
        }
    }

    private var currentDialogue: Dialogue = gameRepository.getInitialDialogue()

//    fun getCurrentDialogue(): Dialogue {
//        return currentDialogue
//    }

    // Функция для переключения на следующий диалог без выбора пользователя
//    fun goToNextDialogue() {
//        currentDialogueIndex++
//        currentDialogue = gameRepository.getDialogueByIndex(currentDialogueIndex)
//            ?: throw IllegalStateException("No dialogue found for index: $currentDialogueIndex")
//    }
}