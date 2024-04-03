package com.pavlovalexey.torpedo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pavlovalexey.torpedo.model.Option
import com.pavlovalexey.torpedo.model.Resource
import com.pavlovalexey.torpedo.model.Scene
import com.pavlovalexey.torpedo.repository.GameRepository

class MainViewModel(val gameRepository: GameRepository) : ViewModel() {
    private val _currentDialogueIndex = MutableLiveData(0)
    val currentDialogueIndex: LiveData<Int>
        get() = _currentDialogueIndex

    private val _currentScene = MutableLiveData<Scene>()
    val currentScene: LiveData<Scene>
        get() = _currentScene

    private val _resources = MutableLiveData<Resource>()
    val resources: LiveData<Resource>
        get() = _resources

    init {
        _currentScene.value = gameRepository.getInitialScene()
        _resources.value = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
    }

    fun selectOption(optionIndex: Int) {
        val currentDialogueIndex = _currentDialogueIndex.value ?: 0
        val selectedOption =
            gameRepository.getDialogueByIndex(currentDialogueIndex)?.options?.getOrNull(optionIndex)
        selectedOption?.let { option ->
            val nextDialogueIndex = option.nextDialogueIndex
            val currentResource = _resources.value ?: Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
            _resources.value = Resource(
                currentResource.rubles + (option.resourceEffect?.rubles ?: 0),
                currentResource.fame + (option.resourceEffect?.fame ?: 0),
                currentResource.teamLoyalty + (option.resourceEffect?.teamLoyalty ?: 0),
                currentResource.vodka + (option.resourceEffect?.vodka ?: 0),
                currentResource.maxim + (option.resourceEffect?.maxim ?: 0),
                currentResource.capital + (option.resourceEffect?.capital ?: 0),
                currentResource.necronomicon + (option.resourceEffect?.necronomicon ?: 0),
                currentResource.neisvestno + (option.resourceEffect?.neisvestno ?: 0),
                currentResource.relationship + (option.resourceEffect?.relationship ?: 0)
            )
            // Получаем следующую сцену для диалога
            val nextScene = gameRepository.getDialogueByIndex(nextDialogueIndex ?: 1)?.scene
            // Проверяем, отличается ли следующая сцена от текущей
            nextScene?.let {
                if (it != _currentScene.value) {
                    _currentScene.value = it
                }
            }
            _currentDialogueIndex.value = nextDialogueIndex
        }
    }

    fun goToNextDialogue() {
        val currentDialogueIndex = _currentDialogueIndex.value ?: 0
        _currentDialogueIndex.value = currentDialogueIndex + 1
        // Получаем следующую сцену для диалога
        val nextScene = gameRepository.getDialogueByIndex(currentDialogueIndex + 1)?.scene
        // Проверяем, отличается ли следующая сцена от текущей
        nextScene?.let {
            if (it != _currentScene.value) {
                _currentScene.value = it
            }
        }
    }
}