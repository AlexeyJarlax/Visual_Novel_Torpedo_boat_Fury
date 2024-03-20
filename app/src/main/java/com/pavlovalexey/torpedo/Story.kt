package com.pavlovalexey.torpedo

import com.pavlovalexey.torpedo.data.Character
import com.pavlovalexey.torpedo.data.Dialog
import com.pavlovalexey.torpedo.data.PlotAct
import com.pavlovalexey.torpedo.data.Scene

object Story {
    val characters = listOf(
        Character("Главный герой: ", R.drawable.char0),
        Character("Благоверная невеста: ", R.drawable.char1),
        Character("Боцман: ", R.drawable.char2),
        Character("Лекарь: ", R.drawable.char3),
        Character("Команда: ", R.drawable.char5)
    )

    val plotActs = listOf(
        PlotAct(
            listOf(
                Scene(
                    R.drawable.scen_spb1,
                    R.raw.odettes_theme,
                    listOf(
                        Dialog(characters[0], "Лучики солнца бъют через занавеску, играя на вашем лице..."),
                        Dialog(characters[0], "Пора приступить к работе!"),
                        Dialog(characters[0], "Отправляюсь в адмиралтейство.")
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
                        Dialog(characters[0], "Документы получены, теперь я капитан!"),
                        Dialog(characters[4], "Готовы к приказам, капитан!")
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
                        Dialog(characters[0], "Дорогая, я должен отправиться в далекий поход."),
                        Dialog(characters[1], "О нет, я так боюсь оставаться одна!"),
                        Dialog(characters[0], "Все будет хорошо, я обязательно вернусь к тебе.")
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
                        Dialog(characters[0], "Рад встрече, товарищи!"),
                        Dialog(characters[2], "Добро пожаловать на борт, капитан!"),
                        Dialog(characters[3], "Надеюсь, вы не будете нуждаться в моих услугах, капитан.")
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