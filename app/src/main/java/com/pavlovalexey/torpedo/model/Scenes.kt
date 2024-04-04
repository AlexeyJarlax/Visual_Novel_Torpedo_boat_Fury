package com.pavlovalexey.torpedo.model

import com.pavlovalexey.torpedo.R

/** определяем сцены по номерам. Каждый номер используется в определенном количестве диалогов, выбор происходит в методе ниже.*/

object Scenes {

    val list: List<Scene> = listOf(
        Scene(R.drawable.scen_spb1, "0"), // Петербург
        Scene(R.drawable.scen_spb2, "1"), // Петербург
        Scene(R.drawable.scen_ships03, "2"), // Корабль
        Scene(R.drawable.scen_ships06, "3"), // Корабль
        Scene(R.drawable.scen_ships04, "4"), // Корабль
        Scene(R.drawable.scen_ships08, "5"), // Корабль
        Scene(R.drawable.bookseller2, "6"), // bookseller помещение
        Scene(R.drawable.bookseller5, "7"), // bookseller улица
        Scene(R.drawable.book, "8"), // book
        Scene(R.drawable.char12, "9"), // Капитан-поручик Резцов - офицер разведки
        Scene(R.drawable.lady00, "10"), // Благоверная невеста
        Scene(R.drawable.lady01, "11") // Благоверная невеста
    )
}