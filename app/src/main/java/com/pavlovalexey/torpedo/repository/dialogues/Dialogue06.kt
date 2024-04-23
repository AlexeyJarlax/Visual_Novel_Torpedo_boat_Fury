package com.pavlovalexey.torpedo.repository.dialogues

import com.pavlovalexey.torpedo.model.Characters.admiral
import com.pavlovalexey.torpedo.model.Characters.bumblebee
import com.pavlovalexey.torpedo.model.Characters.end
import com.pavlovalexey.torpedo.model.Characters.novikov
import com.pavlovalexey.torpedo.model.Characters.paramonov
import com.pavlovalexey.torpedo.model.Characters.reztsov
import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Option
import com.pavlovalexey.torpedo.model.Resource

/** ГЛАВА 6 Танжер, Марокко*/

object Dialogue06 {

    internal val dialogues: List<Pair<Int, Dialogue>> = listOf(

        151 to Dialogue(
            text = "Танжер, Марокко.::",
            options = listOf()
        ),

        152 to Dialogue(
            text = "Тут эскадра должна разделиться. Часть судов, осадка которых позволяет пройти через Суэцкий канал, пойдёт под командой адмирала Фелькерзама этим путем. Рожественский " +
                    "с главными силами поведёт корабли кругом Африки. Оба отряда соединятся на Мадагаскаре",
            scene = scenes[5],
            options = listOf()
        ),

        153 to Dialogue(
            text = "«Что де́лать?",
            options = listOf(
                Option(
                    text = "Следующая глава",
                    nextDialogueIndex = 201,
                )
            )
        ),
    )
}