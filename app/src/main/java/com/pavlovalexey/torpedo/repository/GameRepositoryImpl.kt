package com.pavlovalexey.torpedo.repository

/** основной блок кода сюжета Visual Novel "Torpedo Boat Grozny, содержащий в себе сцены, диалоги и прочие детали сюжета. Сюжет пишется только тут.*/

import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Option
import com.pavlovalexey.torpedo.model.Resource
import com.pavlovalexey.torpedo.model.Scene

class GameRepositoryImpl : GameRepository {

/** определяем сцены по номерам. Каждый номер используется в определенном количестве диалогов, выбор происходит в методе ниже.*/
    private val scenes: List<Scene> = listOf(
        Scene(R.drawable.scen_spb1, "Сцена 1"),
        Scene(R.drawable.scen_spb2, "Сцена 2"),
        Scene(R.drawable.scen_orel, "Сцена 3"),
    )

/** код ниже отвечает за выбор сцены для диалогов.
 * Например сцена 0 работает на диалоги с 0 по 5. И так далее. У каждого диалога как и у сцены свой индекс.*/
    override fun getSceneByDialogueIndex(dialogueIndex: Int): Scene {
        return if (dialogueIndex in 0..5) { // вводная часть игры, определяем режим сложности
            scenes[0]
        } else if (dialogueIndex in 6..8) { // герой проснулся в Петербурге и входит в должность
            scenes[1]
        } else if (dialogueIndex in 9..20) { // герой заходит на корабль
            scenes[2]
        } else {
            throw IllegalStateException("Scene not found for dialogue index: $dialogueIndex")
        }
    }

/** получаем текущий диалог*/
    override fun getInitialDialogue(): Dialogue {
        return dialogues.firstOrNull()?.second ?: throw IllegalStateException("Доступных диалогов нет")
    }

/** получаем индекс диалога*/
    override fun getDialogueByIndex(index: Int): Dialogue? {
        return dialogues.find { it.first == index }?.second
    }

/** получаем текущую сцену*/
    override fun getInitialScene(): Scene {
        return scenes.first()
    }

    /**
     * В этом коде реализована логика для управления сюжетом.
     * Основные компоненты кода:
     * Pair(0, Dialogue(                                 Пара (индекс диалога, объект диалога)
     **** text = "",                                     Текст диалога
     **** options = listOf(                              Опция диалога (список объектов типа опция, представляющий выбор из вариантов ответа (каждый объект вариант)
     ******* Option(                                     Объект вариант ответа
     ********** text = "",                               Текст варианта ответа
     ********** nextDialogueIndex = 1,                   Следующий диалог, при выборе этого варианта
     ********** resourceEffect = Resource(10, 10, 10)    Эффект на ресурсы (Рубли, слава, верность команды)
     */

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


}