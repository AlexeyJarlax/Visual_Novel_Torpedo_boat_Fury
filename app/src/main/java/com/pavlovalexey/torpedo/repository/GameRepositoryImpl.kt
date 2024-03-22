package com.pavlovalexey.torpedo.repository

import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Option
import com.pavlovalexey.torpedo.model.Resource

class GameRepositoryImpl : GameRepository {

    private val dialogues: List<Pair<Int, Dialogue>> = listOf(
        Pair(0, Dialogue(
            text = "Выберите режим игры",
            options = listOf(
                Option(
                    text = "Историческое повествование",
                    nextDialogueIndex = 1,
                    resourceEffect = Resource(10, 10, 10)
                ),
                Option(
                    text = "Альтернативная история",
                    nextDialogueIndex = 2,
                    resourceEffect = Resource(10, 10, 10)
                ),
                Option(
                    text = "Борьба за выживание",
                    nextDialogueIndex = 3,
                    resourceEffect = Resource(7, 7, 7)
                )
            )
        )),
        Pair(1, Dialogue(
            text = "Начало игры в режиме Историческое повествование (повествование в игре и выбор случайных событий в процессе игры приближены к историческим событиям и реалиям 1905 года)",
            options = listOf(
                Option(
                    text = "далее",
                    nextDialogueIndex = 4,
                    resourceEffect = Resource(0, 0, 0)
                ),
                Option(
                    text = "назад",
                    nextDialogueIndex = 0,
                    resourceEffect = Resource(0, 0, 0)
                )
            )
        )),
        Pair(2, Dialogue(
            text = "Начало игры в режиме Альтернативная история (повествование в игре и выбор случайных событий в процессе игры могут привести к неожиданным результатам, содержит элементы фантастики)",
            options = listOf(
                Option(
                    text = "далее",
                    nextDialogueIndex = 4,
                    resourceEffect = Resource(0, 0, 0)
                ),
                Option(
                    text = "назад",
                    nextDialogueIndex = 0,
                    resourceEffect = Resource(0, 0, 0)
                )
            )
        )),
        Pair(3, Dialogue(
            text = "Начало игры в режиме Борьба за выживание (режим игры с повышенной сложностью; повествование в игре и выбор случайных событий в процессе игры могут привести к неожиданным результатам, содержит элементы фантастики)",
            options = listOf(
                Option(
                    text = "далее",
                    nextDialogueIndex = 4,
                    resourceEffect = Resource(0, 0, 0)
                ),
                Option(
                    text = "назад",
                    nextDialogueIndex = 0,
                    resourceEffect = Resource(0, 0, 0)
                )
            )
        )),
        Pair(4, Dialogue(
            text = "Лучики солнца бъют через занавеску, играя на вашем лице...",
            options = listOf()
        )),
        Pair(5, Dialogue(
            text = "Введение...",
            options = listOf()
        )),
        Pair(6, Dialogue(
            text = "Введение....",
            options = listOf()
        )),
        Pair(7, Dialogue(
            text = "Введение.....",
            options = listOf()
        )),
        Pair(8, Dialogue(
            text = "Введение......",
            options = listOf()
        )),
        Pair(9, Dialogue(
            text = "Введение........",
            options = listOf()
        )),
        Pair(10, Dialogue(
            text = "Какие будут первые распоряжения, капитан?",
            options = listOf(
                Option(
                    text = "Плывём грабить корованы!",
                    nextDialogueIndex = 11,
                    resourceEffect = Resource(2, 0, 0)
                ),
                Option(
                    text = "Плывём поможем царевичу!",
                    nextDialogueIndex = 11,
                    resourceEffect = Resource(0, 2, 0)
                ),
                Option(
                    text = "Пусть команда отдохнёт перед походом",
                    nextDialogueIndex = 11,
                    resourceEffect = Resource(0, 0, 2)
                )
            )
        )),
        Pair(11, Dialogue(
            text = "Конец игры",
            options = listOf()
        )),
        // Добавляем пары индекса и диалога
    )

    override fun getInitialDialogue(): Dialogue {
        return dialogues.firstOrNull()?.second ?: throw IllegalStateException("No dialogues available")
    }

    override fun getDialogueByIndex(index: Int): Dialogue? {
        return dialogues.find { it.first == index }?.second
    }
}