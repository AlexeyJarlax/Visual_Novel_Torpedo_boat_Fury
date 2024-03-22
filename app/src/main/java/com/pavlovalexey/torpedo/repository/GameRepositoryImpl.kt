package com.pavlovalexey.torpedo.repository

import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Option
import com.pavlovalexey.torpedo.model.Resource

class GameRepositoryImpl : GameRepository {

    private val dialogues: List<Dialogue> = listOf(
        Dialogue(
            text = "Привет! Это начало игры.",
            options = listOf(
                Option(
                    text = "Ответ 1",
                    nextDialogueIndex = 1,
                    resourceEffect = Resource(1, 0, 0)
                ),
                Option(
                    text = "Ответ 2",
                    nextDialogueIndex = 2,
                    resourceEffect = Resource(0, 1, 0)
                ),
                Option(
                    text = "Ответ 3",
                    nextDialogueIndex = 3,
                    resourceEffect = Resource(0, 0, 1)
                )
            )
        ),
        Dialogue(
            text = "Диалог 2",
            options = listOf(
                // опции для второго диалога
            )
        ),
        Dialogue(
            text = "Диалог 3",
            options = listOf(
                // опции для третьего диалога
            )
        ),
        Dialogue(
            text = "Подготовка к морскому походу",
            options = listOf(
                Option(
                    text = "Проверить состояние корабля",
                    nextDialogueIndex = 4,
                    resourceEffect = Resource(0, 0, 0) // Пока без эффекта на ресурсы
                ),
                Option(
                    text = "Провести инструктаж экипажа",
                    nextDialogueIndex = 5,
                    resourceEffect = Resource(0, 0, 0) // Пока без эффекта на ресурсы
                ),
                Option(
                    text = "Подготовить запасы провизии",
                    nextDialogueIndex = 6,
                    resourceEffect = Resource(0, 0, 0) // Пока без эффекта на ресурсы
                )
            )
        ),
        Dialogue(
            text = "Проверка состояния корабля: Все системы работают исправно. Готовы к отплытию!",
            options = listOf(
                // опции после проверки корабля
            )
        ),
        Dialogue(
            text = "Инструктаж экипажа: Экипаж получил инструктаж. Все знают свои обязанности.",
            options = listOf(
                // опции после инструктажа
            )
        ),
        Dialogue(
            text = "Подготовка запасов провизии: Провизия погружена на борт и готова к использованию.",
            options = listOf(
                // опции после подготовки провизии
            )
        ),
        Dialogue(
            text = "Боцман: Капитан, какие приказания по распределению экипажа на борту?",
            options = listOf(
                Option(
                    text = "Распределить по привычному графику",
                    nextDialogueIndex = 7,
                    resourceEffect = Resource(0, 0, 0) // Пока без эффекта на ресурсы
                ),
                Option(
                    text = "Изменить распределение сил в зависимости от задач",
                    nextDialogueIndex = 8,
                    resourceEffect = Resource(0, 0, 0) // Пока без эффекта на ресурсы
                ),
                Option(
                    text = "Провести дополнительный инструктаж по боевым действиям",
                    nextDialogueIndex = 9,
                    resourceEffect = Resource(0, 0, 0) // Пока без эффекта на ресурсы
                )
            )
        ),
        // Здесь можно добавить дополнительные диалоги для различных сценариев
    )

    override fun getInitialDialogue(): Dialogue {
        return dialogues.firstOrNull() ?: throw IllegalStateException("No dialogues available")
    }

    override fun getDialogueByIndex(index: Int): Dialogue? {
        return dialogues.getOrNull(index)
    }
}