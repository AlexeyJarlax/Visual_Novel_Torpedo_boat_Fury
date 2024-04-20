package com.pavlovalexey.torpedo.repository.dialogues

import com.pavlovalexey.torpedo.model.Characters.bookseller
import com.pavlovalexey.torpedo.model.Characters.reztsov
import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Option
import com.pavlovalexey.torpedo.model.Resource

/** ГЛАВА 7 НЕОБЫЧНАЯ КНИГА ПРОДОЛЖЕНИЕ */

object Dialogue07 {

    private var currentResource: Resource = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)

    internal fun setCurrentResource(resource: Resource) {
        currentResource = resource
    }

    internal val dialogues: List<Pair<Int, Dialogue>> = listOf(

        201 to Dialogue(
            text = "Решив прогуляться по городу я зашёл на местный рынок и через некоторое время ко мне подошёл мужчина вполне обыкновенного для местности вида, но вёл себя он странно - " +
                    "оглядывался так, как-будто хотел сделать что-то противозаконное",
            scene = scenes[15],
            options = listOf()
        ),

        202 to Dialogue(
            text = "Капитан-пороучик Резцов, сопровождавший меня видимо сделал те же выводы, так как встал теперь между мной и этим сударем...",
            scene = scenes[9],
            options = listOf()
        ),

        203 to Dialogue(
            text = "$bookseller::: Не интересуют ли господ книги? У меня как раз есть один необычный экземпляр, вряд ли вы увидите такую у простых лавочников...",
            scene = scenes[7],
            options = listOf()
        ),

        204 to Dialogue(
            text = "$reztsov::: Ходебщик, так их у нас называют... Это букинисты, которые носят и предлагают к продаже книги",
            scene = scenes[9],
            options = listOf()
        ),

        205 to Dialogue(
            text = "Заинтригованный, я прошу показать что за книгу он хочет предложить",
            scene = scenes[7],
            options = listOf()
        ),

        206 to Dialogue(
            text = "Ходебщик разворачивает перекидной мешок, и достает толстую книгу, протягивая ее мне",
            options = if (currentResource.capital > 1) { // Проверяем значение
                listOf(
                    Option(
                        text = "Взглянув на обложку, разочарованно понимаю, что эту книгу читал, и она не представляет интереса",
                        nextDialogueIndex = 209,
                    ),
                )
            } else {
                listOf(
                    Option(
                        text = "На незнакомой обложке красуется название: Что делать? Наболевшие вопросы нашего движения» — В. И. Ленин",
                        nextDialogueIndex = 207,
//                        NotUsingThisCaseIfCapitalIsTrue = true // Устанавливаем значение, блокирующее этот вариант ответа для случая, если книга Капитал уже имеется
                    )
                )
            },
        ),

        207 to Dialogue(
            text = "Я открываю последниен страницы книги и в библиографических ссылках смотрю, нет ли знакомых источников. К своему удивлению нахожу книгу, которую начал недавно читать.",
            options = listOf(
                Option(
                    text = "Купить книгу (-49 Царских Рублей)",
                    nextDialogueIndex = 208,
                    resourceEffect = Resource(-49, 0, 0, 0, 0, 1, 0, 0, 0)
                ),
                Option(
                    text = "Отказаться от покупки",
                    nextDialogueIndex = 209,
                )
            )
        ),

        208 to Dialogue(
            text = "Расчитавшись с торговцем беру книгу из его дрожащих рук...",
            options = listOf()
        ),

        209 to Dialogue(
            text = "На рынке больше нечего смотреть и нам пора вернуться на корабль",
            scene = scenes[15],
            options = listOf(
                Option(
                    text = "Следующая глава",
                    nextDialogueIndex = 210,
                )
            )
        ),
        210 to Dialogue(
            text = "Конец дня, я могу уединиться в своей каюте и полистать одну из книг коллекции",
            scene = scenes[8],
            options = listOf(
                Option(
                    text = "(чтение книг происходит автоматически в конце главы, оно не влияет на сюжет и может быть пропущено)",
                    nextDialogueIndex = 211
                )
            )
        ),
        211 to Dialogue(
//            text = getNextBookFragment(), // Получаем следующий фрагмент книги
            options = listOf(
                Option(
                    text = "следующая страница",
                    nextDialogueIndex = 211,
                ),
                Option(
                    text = "закрыть книгу",
                    nextDialogueIndex = 301
                )
            )
        ),
    )
}