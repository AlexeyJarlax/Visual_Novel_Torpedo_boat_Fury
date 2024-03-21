package com.pavlovalexey.torpedo.presentation

import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.data.Character
import com.pavlovalexey.torpedo.data.Dialog
import com.pavlovalexey.torpedo.data.PlotAct
import com.pavlovalexey.torpedo.data.Scene

object Story {
    // Герои
    val story = "" // рассказчик
    val hero = "" // герой, тоже пустое поле
    val bride = "Благоверная невеста: "
    val boatswain = "Боцман: "
    val doc = "Лекарь: "
    val team = "Команда: "

    val characters = listOf(
        Character(story),
        Character(hero),
        Character(bride),
        Character(boatswain),
        Character(doc),
        Character(team)
    )

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
                        Dialog(boatswain, "Какие будут распоряжения, капитан?", listOf("Провести ревизию вооружения", "Осмотреть команду")),
                        Dialog(story, "молчание"),
                        Dialog(hero, "Провести ревизию вооружения"),
                        Dialog(boatswain, "Хорошо!")
                    )
                )
            )
        )
    )

    // Дополним объект Story функцией, которая обрабатывает выбор пользователя и возвращает следующий диалог
    fun processChoice(option: String, dialogViewModel: DialogViewModel): Dialog? {
        // Ваша логика обработки выбора и возврата следующего диалога
        // Здесь можно использовать свитч или if-else для различных вариантов выбора
        // В данном примере просто возвращаем следующий диалог после выбора одного из вариантов
        return dialogViewModel.getNextDialog()
    }

    private fun countScenesInActs(): IntArray {
        val sceneCounts = IntArray(plotActs.size)
        for (i in plotActs.indices) {
            sceneCounts[i] = plotActs[i].scenes.size
        }
        return sceneCounts
    }

    val sceneCounts = countScenesInActs()
}
