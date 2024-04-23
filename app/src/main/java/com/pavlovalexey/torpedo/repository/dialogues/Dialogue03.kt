package com.pavlovalexey.torpedo.repository.dialogues

import com.pavlovalexey.torpedo.model.Characters.bookseller
import com.pavlovalexey.torpedo.model.Characters.reztsov
import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Option
import com.pavlovalexey.torpedo.model.Resource

/** ГЛАВА 3 НЕОБЫЧНАЯ КНИГА: КАПИТАЛ*/

object Dialogue03 {

    private var currentResource: Resource =
        Resource(0, 0, 0, 0, 0, 1, 0, 0, 0)// Define currentResource

    internal fun setCurrentResource(resource: Resource) {
        currentResource = resource
    }

    internal val dialogues: List<Pair<Int, Dialogue>> = listOf(
        100 to Dialogue(
            text = "Решив прогуляться по городу я зашёл на местный рынок и через некоторое время, сам того не заметив уже изучал книги в большой книжной лавке",
            scene = scenes[4],
            options = listOf()
        ),

        101 to Dialogue(
            text = "Капитан-поручик Резцов, сопровождавший меня на берегу в целях безопастности, держался рядом, со скучающим видом листая газету",
            scene = scenes[9],
            options = listOf()
        ),

        102 to Dialogue(
            text = "$bookseller::: Не интересуют ли господ книги? У меня как раз есть один ценный и необычный экземпляр, вряд ли вы увидите такую у простых лавочников...",
            scene = scenes[6],
            options = listOf()
        ),

        103 to Dialogue(
            text = "$reztsov::: Мы офицеры и люди образованные, вряд ли вы сможете нас чем-то удивить, сударь",
            scene = scenes[9],
            options = listOf()
        ),

        104 to Dialogue(
            text = "Резцов произнёс это на языке торговца практически без акцента, что меня изрядно удивило",
            scene = scenes[9],
            options = listOf()
        ),

        105 to Dialogue(
            text = "Торговец открывает одну из полок его большого письменного стола ключом и достает толстую книгу, протягивая ее мне, так как я стоял ближе",
            scene = scenes[6],
            options = if (currentResource.capital > 1) { // Проверяем значение
                listOf(
                    Option(
                        text = "Взглянув на обложку, разочарованно понимаю, что эту книгу я читал, и интереса она не представляет",
                        nextDialogueIndex = 110,
                        resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                    ),
                )
            } else {
                listOf(
                    Option(
                        text = "На незнакомой обложке красуется название: Капитал. Автор книги: Карл Маркс...",
                        nextDialogueIndex = 106,
                        resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                    )
                )
            },
        ),

        106 to Dialogue(
            text = "$bookseller::: Вы ведь русские, да? Скромные 149 Российских Царских рублей господа! Уверяю вас, вы не пожалеете!",
            scene = scenes[6],
            options = listOf(
                Option(
                    text = "Купить книгу (ВНИМАНИЕ! Добавление книги в коллекцию может создать альтернативную историю развития событий)",
                    nextDialogueIndex = 109,
                    resourceEffect = Resource(-149, 0, 0, 0, 0, 1, 0, 0, 0),
                    optionFunction = { currentResource.capital += 1 }
                ),
                Option(
                    text = "Отказаться от покупки",
                    nextDialogueIndex = 110,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),

        109 to Dialogue(
            text = "Расчитавшись с торговцем беру книгу из его морщинистых рук...",
            scene = scenes[6],
            options = listOf()
        ),

        110 to Dialogue(
            text = "Тут больше нечего смотреть и нам пора вернуться на корабль",
            scene = scenes[4],
            options = listOf()
        ),

        111 to Dialogue(
            text = "Конец дня, я могу уединиться в своей каюте и полистать одну из книг коллекции",
            scene = scenes[8],
            options = if (currentResource.capital > 0) {
                listOf(
                    Option(
                        text = "(чтение книг происходит автоматически в конце главы, оно не влияет на сюжет и может быть пропущено)",
                        nextDialogueIndex = 112,
                        resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                    )
                )
            } else {
                listOf(
                    Option(
                        text = "(на полке не оказалось непрочитанных книг...)",
                        nextDialogueIndex = 113,
                        resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                    )
                )
            }

        ),

        112 to Dialogue(
//            text = getNextBookFragment(), // Получаем следующий фрагмент книги
            scene = scenes[8],
            options = listOf(
                Option(
                    text = "следующая страница",
                    nextDialogueIndex = 112, // Оставляем тот же индекс диалога
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                ),
                Option(
                    text = "закрыть книгу",
                    nextDialogueIndex = 113,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),
    )
}