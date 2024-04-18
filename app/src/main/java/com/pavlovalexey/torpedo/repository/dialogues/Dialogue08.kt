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

/** ГЛАВА 8 Суэцкий канал */

object Dialogue08 {

    internal val dialogues: List<Pair<Int, Dialogue>> = listOf(

        301 to Dialogue(
            text = "Суэцкий канал, Египет::",
            scene = scenes[15],
            options = listOf()
        ),

        302 to Dialogue(
            options = listOf(
                Option(
                    text = "Следующая глава",
                    nextDialogueIndex = 351,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),
    )
}


///** ГЛАВА 7 Мадагаскар */
//
//351 to Dialogue(
//text = "Мадагаскар.::",
//scene = scenes[14],
//options = listOf()
//),
//
//352 to Dialogue(
//text = "25 декабря 1904 года.",
//options = listOf(
//Option(
//text = "Следующая глава",
//nextDialogueIndex = 401,
//)
//)
//),
//
///** ГЛАВА 8 присоединился отряд Добротворского. Малакский пролив*/
//
//401 to Dialogue(
//text = "Малакский пролив::",
//options = listOf()
//),
//
//402 to Dialogue(
//text = "1 февраля 1905 года. Присоединился отряд Добротворского. 3 марта эскадра вышла в море, проложив курс к Малакскому проливу",
//options = listOf(
//Option(
//text = "Следующая глава",
//nextDialogueIndex = 451,
//resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
//)
//)
//),
//
///** ГЛАВА 9 бухта Камран */
//
//451 to Dialogue(
//text = "бухта Камран, Сайгон.::",
//options = listOf()
//),
//
//452 to Dialogue(
//text = "1 апреля 1905 года. Эскадра прибыла в бухту Камран (на Индо-Китайском п-ве, вблизи Сайгона)",
//options = listOf(
//Option(
//text = "Следующая глава",
//nextDialogueIndex = 501,
//resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
//)
//)
//),
//
///** ГЛАВА 10 бухта Ван-Фонг, */
//
//501 to Dialogue(
//text = "бухта Ван-Фонг, Сайгон.::",
//options = listOf()
//),
//
//502 to Dialogue(
//text = "24 апреля 1905 года. Прибыл отряд Небогатова.",
//options = listOf(
//Option(
//text = "Следующая глава",
//nextDialogueIndex = 560,
//resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
//)
//)
//),
//
///** ГЛАВА 11 Цусимское сражение */
//
//560 to Dialogue(
//text = "Цусимское сражение.::",
//options = listOf()
//),
//561 to Dialogue(
//text = "Продолжение следует...",
//options = listOf(
//)
//),