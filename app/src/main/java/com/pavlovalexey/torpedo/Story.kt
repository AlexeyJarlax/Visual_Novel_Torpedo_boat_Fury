package com.pavlovalexey.torpedo

import com.pavlovalexey.torpedo.data.Character
import com.pavlovalexey.torpedo.data.Dialog
import com.pavlovalexey.torpedo.data.PlotAct
import com.pavlovalexey.torpedo.data.Scene

// класс для истории
object Story {
    // Определяем постоянных персонажей
    val characters = listOf(
        Character("Главный герой", R.drawable.char0),
        Character("Благоверная невеста", R.drawable.char1),
        Character("Боцман", R.drawable.char2),
        Character("Лекарь", R.drawable.char3),
        Character("Команда", R.drawable.char5)
    )

    // Определяем акты сюжета
    val plotActs = listOf(
        // Сцена 0: Главный герой просыпается в Петербурге и направляется на повышение в адмиралтейство.
        PlotAct(
            listOf(
                Scene(
                    R.drawable.scen_spb1,
                    R.raw.odettes_theme,
                    listOf(
                        Dialog("Главный герой", "Пора приступить к работе!"),
                        Dialog("Главный герой", "Отправляюсь в адмиралтейство.")
                    ),
                    "Главный герой просыпается в Петербурге и направляется на повышение в адмиралтейство."
                )
            )
        ),
        // Сцена 1: Главный герой получает документы в адмиралтействе и становится капитаном нового корабля.
        PlotAct(
            listOf(
                Scene(
                    R.drawable.scen_spb2,
                    R.raw.odettes_theme,
                    listOf(
                        Dialog("Главный герой", "Документы получены, теперь я капитан!"),
                        Dialog("Команда", "Готовы к приказам, капитан!")
                    ),
                    "Главный герой получает документы и становится капитаном нового корабля."
                )
            )
        ),
        // Сцена 2: Главный герой обсуждает будущее с благоверной невестой и утешает ее перед отправлением в далекий поход.
        PlotAct(
            listOf(
                Scene(
                    R.drawable.scen_spb1,
                    R.raw.odettes_theme,
                    listOf(
                        Dialog("Главный герой", "Дорогая, я должен отправиться в далекий поход."),
                        Dialog("Благоверная невеста", "О нет, я так боюсь оставаться одна!"),
                        Dialog("Главный герой", "Все будет хорошо, я обязательно вернусь к тебе.")
                    ),
                    "Главный герой обсуждает будущее с благоверной невестой и утешает ее перед отправлением в далекий поход."
                )
            )
        ),
        // Сцена 3: Главный герой знакомится с боцманом, корабельным лекарем и командой на своем корабле.
        PlotAct(
            listOf(
                Scene(
                    R.drawable.scen_orel,
                    R.raw.odettes_theme,
                    listOf(
                        Dialog("Главный герой", "Рад встрече, товарищи!"),
                        Dialog("Боцман", "Добро пожаловать на борт, капитан!"),
                        Dialog("Лекарь", "Надеюсь, вы не будете нуждаться в моих услугах, капитан.")
                    ),
                    "Главный герой знакомится с боцманом, корабельным лекарем и командой на своем корабле."
                )
            )
        )
    )

    // Функция для подсчета общего количества сцен в каждом акте
    private fun countScenesInActs(): IntArray {
        val sceneCounts = IntArray(plotActs.size)
        for (i in plotActs.indices) {
            sceneCounts[i] = plotActs[i].scenes.size
        }
        return sceneCounts
    }

    // Общее количество сцен в каждом акте
    val sceneCounts = countScenesInActs()
}