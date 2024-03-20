package com.pavlovalexey.torpedo

import com.pavlovalexey.torpedo.data.Character
import com.pavlovalexey.torpedo.data.Dialog
import com.pavlovalexey.torpedo.data.PlotAct
import com.pavlovalexey.torpedo.data.Scene

object Story {
    val characters = listOf(
        Character(""), // расказчик
        Character("Главный герой: "),
        Character("Благоверная невеста: "),
        Character("Боцман: "),
        Character("Лекарь: "),
        Character("Команда: ")
    )

    // ГЕРОИ
    val story = "" // расказчик
    val hero = ""
    val bride = "Благоверная невеста: "
    val boatswain = "Боцман: "
    val doc = "Лекарь: "
    val team = "Команда: "

    val plotActs = listOf(
        PlotAct(
            listOf(
                Scene(
                    R.drawable.scen_spb1,
                    R.raw.odettes_theme,
                    listOf(
                        Dialog(story, "Лучики солнца бъют через занавеску, играя на вашем лице..."),
                        Dialog(hero, "Пора войти в должность!"),
                        Dialog(hero, "Отправляюсь в адмиралтейство.")
                    )
                )
            )
        ),
        PlotAct(
            listOf(
                Scene(
                    R.drawable.scen_spb2,
                    R.raw.odettes_theme,
                    listOf(
                        Dialog(hero, "Документы получены, теперь я капитан!"),
                        Dialog(hero, "Великолепный день!")
                    )
                )
            )
        ),
        PlotAct(
            listOf(
                Scene(
                    R.drawable.scen_spb1,
                    R.raw.odettes_theme,
                    listOf(
                        Dialog(hero, "Дорогая, я должен отправиться в далекий поход..."),
                        Dialog(bride, "О нет!"),
                        Dialog(hero, "Все будет хорошо, я обязательно вернусь к тебе."),
                        Dialog(bride, "Так и думала, что продинямят, я так боюсь оставаться одна!"),
                        Dialog(hero, "ОЙ ВСЁ!")
                    )
                )
            )
        ),
        PlotAct(
            listOf(
                Scene(
                    R.drawable.scen_orel,
                    R.raw.odettes_theme,
                    listOf(
                        Dialog(hero, "Рад встрече, господа!"),
                        Dialog(boatswain, "Добро пожаловать на борт, капитан!"),
                        Dialog(doc, "А мы то как рады! открывате ром мужики!"),
                        Dialog(team, "Отличное начало похода!"),
                        )
                    )
                )
            )
        )


    private fun countScenesInActs(): IntArray {
        val sceneCounts = IntArray(plotActs.size)
        for (i in plotActs.indices) {
            sceneCounts[i] = plotActs[i].scenes.size
        }
        return sceneCounts
    }

    val sceneCounts = countScenesInActs()
}