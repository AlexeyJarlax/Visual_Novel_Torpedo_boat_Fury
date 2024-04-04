package com.pavlovalexey.torpedo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pavlovalexey.torpedo.model.Option
import com.pavlovalexey.torpedo.model.Resource
import com.pavlovalexey.torpedo.model.Scene
import com.pavlovalexey.torpedo.repository.GameRepository

class MainViewModel(private val resource: Resource, val gameRepository: GameRepository) : ViewModel() {

    private val _currentDialogueIndex = MutableLiveData(0)
    val currentDialogueIndex: LiveData<Int>
        get() = _currentDialogueIndex

    private val _currentScene = MutableLiveData<Scene>()
    val currentScene: LiveData<Scene>
        get() = _currentScene

    private val _resources = MutableLiveData<Resource>() // Удаляем инициализацию, так как передаем объект через конструктор
    val resources: LiveData<Resource>
        get() = _resources

    init {
        _currentScene.value = gameRepository.getInitialScene()
        _resources.value = resource // Используем переданный объект resource
    }

    fun selectOption(optionIndex: Int) {
        val currentDialogueIndex = _currentDialogueIndex.value ?: 0
        val selectedOption = gameRepository.getDialogueByIndex(currentDialogueIndex)?.options?.getOrNull(optionIndex)
        selectedOption?.let { option ->
            val resourceEffect = option.resourceEffect ?: Resource(9, 9, 9, 9, 9, 9, 9, 9, 9) // Ресурс по умолчанию
            gameRepository.updateResources(resourceEffect) // Обновляем ресурсы в репозитории с передачей объекта resourceEffect
            val currentResource = _resources.value ?: Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
            _resources.value = Resource(
                currentResource.rubles + resourceEffect.rubles,
                currentResource.fame + resourceEffect.fame,
                currentResource.teamLoyalty + resourceEffect.teamLoyalty,
                currentResource.vodka + resourceEffect.vodka,
                currentResource.maxim + resourceEffect.maxim,
                currentResource.capital + resourceEffect.capital,
                currentResource.necronomicon + resourceEffect.necronomicon,
                currentResource.neisvestno + resourceEffect.neisvestno,
                currentResource.relationship + resourceEffect.relationship
            )
            // Получаем следующую сцену для диалога
            val nextDialogueIndex = option.nextDialogueIndex
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