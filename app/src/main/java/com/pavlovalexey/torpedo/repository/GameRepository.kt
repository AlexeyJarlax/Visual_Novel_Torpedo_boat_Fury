package com.pavlovalexey.torpedo.repository

import androidx.lifecycle.LiveData
import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Resource
import com.pavlovalexey.torpedo.model.Scene

interface GameRepository {
    fun getInitialDialogue(): Dialogue // Возвращает начальный диалог
    fun getDialogueByIndex(index: Int): Dialogue? // Возвращает диалог по индексу
    fun getInitialScene(): Scene // Возвращает начальную сцену
    fun getResource(): Resource // Возвращает ресурсы
    fun updateResources(resourceEffect: Resource)    // Функция для обновления ресурсов на основе эффекта ресурсов
    fun getNextBookFragment(): String    // Функция для получения следующего фрагмента книги
    fun updateDialogueWithNextFragment(dialogue: Dialogue, nextFragment: String)    // Функция для обновления текста диалога на основе последнего прочитанного фрагмента книги
}