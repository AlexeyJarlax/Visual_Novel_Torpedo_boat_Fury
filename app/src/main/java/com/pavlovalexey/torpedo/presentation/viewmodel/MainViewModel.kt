package com.pavlovalexey.torpedo.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pavlovalexey.torpedo.model.Resource
import com.pavlovalexey.torpedo.model.Scene
import com.pavlovalexey.torpedo.repository.GameRepository
import com.pavlovalexey.torpedo.repository.dialogues.Dialogue07

/** Стандартная вью модель с пробросом функций на RepositoryImpl через интерфейс. Схема активити - сингл + фрагменты Основные расчеты в GameRepositoryImpl*/

class MainViewModel(val gameRepository: GameRepository) : ViewModel() {

    private val _currentDialogueIndex = MutableLiveData(0)
    val currentDialogueIndex: LiveData<Int>
        get() = _currentDialogueIndex

    private val _currentScene = MutableLiveData<Scene>()
    val currentScene: LiveData<Scene>
        get() = _currentScene

    private val _resources = MutableLiveData(Resource(0, 0, 0, 0, 0, 0, 0, 0, 0))
    val resources: LiveData<Resource>
        get() = _resources

    val initialResources: LiveData<Resource> = MutableLiveData<Resource>().apply {
        value = gameRepository.loadResources()
    }

    init {
        _currentScene.value = gameRepository.getInitialScene()
        Dialogue07.setCurrentResource(_resources.value!!)

        val savedIndex = gameRepository.loadCurrentDialogueIndex()
        if (savedIndex != 0) {
            _currentDialogueIndex.value = savedIndex
            _currentScene.value = gameRepository.getDialogueByIndex(savedIndex)?.scene
                ?: gameRepository.getInitialScene()
        } else {
            // Если индекс не сохранен, начинаем с начального диалога
            _currentDialogueIndex.value = 0
            _currentScene.value = gameRepository.getInitialScene()
        }
    }

    fun setCurrentDialogueIndex(index: Int) {
        _currentDialogueIndex.value = index
    }

    fun setCurrentScene(scene: Scene) {
        _currentScene.value = scene
    }

    fun selectOption(optionIndex: Int) {
        Log.d("===", "Main VM selectOption - optionIndex: $optionIndex")
        val currentDialogueIndex = _currentDialogueIndex.value ?: 0
        val selectedOption =
            gameRepository.getDialogueByIndex(currentDialogueIndex)?.options?.getOrNull(optionIndex)

        if (currentDialogueIndex == 0) {
            resetResources()
        }

        selectedOption?.let { option ->
            val resourceEffect =
                option.resourceEffect ?: Resource(0, 0, 0, 0, 0, 0, 0, 0, 0) // Ресурс по умолчанию
            Log.d("===", "Main VM selectOption - resourceEffect: $resourceEffect")
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
            /*gameRepository.updateResources(currentResource)*/
            gameRepository.updateResources(
                option.resourceEffect ?: Resource(
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0,
                    0
                )
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

    fun resetResources() {
        _resources.value = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0) // Устанавливаем все ресурсы в ноль
    }

    fun goToNextDialogue() {
        val currentDialogueIndex = _currentDialogueIndex.value ?: 0
        _currentDialogueIndex.value = currentDialogueIndex + 1
        val nextScene = gameRepository.getDialogueByIndex(currentDialogueIndex + 1)?.scene
        nextScene?.let {
            if (it != _currentScene.value) {
                _currentScene.value = it
            }
        }
    }
}