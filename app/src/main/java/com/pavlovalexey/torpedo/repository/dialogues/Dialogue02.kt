package com.pavlovalexey.torpedo.repository.dialogues

import com.pavlovalexey.torpedo.model.Characters.attack
import com.pavlovalexey.torpedo.model.Characters.bumblebee
import com.pavlovalexey.torpedo.model.Characters.reztsov
import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Option
import com.pavlovalexey.torpedo.model.Resource

/** ГЛАВА 2 ГУЛЛЬСКИЙ ИНЦИДЕНТ + ПОРТ БРЕСТ, ФРАНЦИЯ*/

object Dialogue02 {

    internal val dialogues: List<Pair<Int, Dialogue>> = listOf(
        50 to Dialogue(
            text = "** ГУЛЛЬСКИЙ ИНЦИДЕНТ **",
            scene = scenes[5],
            options = listOf()
        ),

        51 to Dialogue(
            text = "РАДИОГРАММА (КАМЧАТКА)::: Преследуют миноносцы.",
            scene = scenes[5],
            options = listOf()
        ),

        52 to Dialogue(
            text = "РАДИОГРАММА (СУВОРОВ)::: За вами погоня? Сколько миноносцев и от какого румба?",
            scene = scenes[5],
            options = listOf()
        ),

        53 to Dialogue(
            text = "РАДИОГРАММА (КАМЧАТКА)::: Атака со всех сторон.",
            scene = scenes[5],
            options = listOf()
        ),

        54 to Dialogue(
            text = "РАДИОГРАММА (СУВОРОВ)::: Сколько миноносцев? Сообщите!",
            scene = scenes[5],
            options = listOf()
        ),

        55 to Dialogue(
            text = "РАДИОГРАММА (КАМЧАТКА)::: Миноносцев около восьми.",
            scene = scenes[5],
            options = listOf()
        ),

        56 to Dialogue(
            text = "РАДИОГРАММА (СУВОРОВ)::: Близко к вам?",
            scene = scenes[5],
            options = listOf()
        ),

        57 to Dialogue(
            text = "РАДИОГРАММА (КАМЧАТКА)::: Более кабельтова.",
            scene = scenes[5],
            options = listOf()
        ),

        58 to Dialogue(
            text = "РАДИОГРАММА (СУВОРОВ)::: Пускали ли мины?",
            scene = scenes[5],
            options = listOf()
        ),

        59 to Dialogue(
            text = "РАДИОГРАММА (КАМЧАТКА)::: Мин не видели.",
            scene = scenes[5],
            options = listOf()
        ),

        60 to Dialogue(
            text = "РАДИОГРАММА (СУВОРОВ)::: Всем кораблям ожидать атаки миноносцев сзади.",
            scene = scenes[5],
            options = listOf()
        ),

        61 to Dialogue(
            text = "Капитан-поручики Шмель и Резцов таже в радиорубке прослушивали этот сеанс связи. При первых сообщениях с транспорта Камчатка на Шмель поднял тревогу на корабле, сейчас " +
                    "осталось ждать распоряжений адмирала и готовиться к возможному бою",
            scene = scenes[5],
            options = listOf()
        ),

        62 to Dialogue(
            text = "$bumblebee ::: Подозрительно мутная ситуация... В Датских водах японские корабли? Или чьи они..? ",
            scene = scenes[5],
            options = listOf()
        ),

        63 to Dialogue(
            text = "После указания двигаться во французский портовый город Брест других приказов миноносцам от адмирала не поступало... " +
                    "Я дал приказ развернуть корабль к флотилии, а наблюдателям рыскать прожекторами... ",
            scene = scenes[5],
            options = listOf()
        ),

        64 to Dialogue(
            text = "Далее началась просто невообразимая суматоха: Телеграф сообщал, что СУВОРОВ включил боевые огни, его примеру последовали остальные корабли. Тут же часть кораблей начала " +
                    "сообщать по телеграфу об обнаружении противника. Поступил приказ от адмирала открыть огонь. И я наконец услышал отдаленный рокот орудий главного калибра.  Мой миноносец " +
                    "шёл далеко впереди от эскадры и начавшаяся суматоха пока нас не затронула...",
            scene = scenes[5],
            options = listOf()
        ),

        65 to Dialogue(
            text = "Один из прожекторов неожиданно нарыскал небольшой корабль, наблюдатель тут же передал по связи, что видит неизвестный миноносец. В связи с тем, что наши миноносцы " +
                    "позади, делаю вывод, что корабль не может быть нашим. Пытаюсь определить корабль по силуэту в штормовом море и под светом прожекторов, но мне не удается",
            scene = scenes[5],
            options = listOf(
                Option(
                    text = attack,
                    nextDialogueIndex = 66,
                ),
                Option(
                    text = "Не вступать в бой!",
                    nextDialogueIndex = 67,
                ),
            )
        ),

        66 to Dialogue(
            text = "Взрыв торпеды осветил яркой вспышкой кусочек моря, и нашу цель, которая внезапно оказалась вовсе не миноносцем... Мы подошли ближе и я дал распоряжение " +
                    "спасательной команде вылавливать людей - матросы и рыбаки прыгали с горящего корабля в воду, кто-то на пароходе пытался спустить шлюпку, но их судно " +
                    "очень быстро тонуло...",
            scene = scenes[5],
            options = listOf(
                Option(
                    text = "Чтож... Плохая известность - это тоже известность. Жаль боезапас потратили на рыбаков",
                    nextDialogueIndex = 68,
                    resourceEffect = Resource(0, 1, 0, 0, -2, 0, 0, 0, 0)
                ),
            )
        ),

        67 to Dialogue(
            text = "Из-за темноты нам пришлось практически полностью сблизиться с кораблём, чтобы понять, что это рыбацкий пароход, а не корабль военного назначения...",
            scene = scenes[5],
            options = listOf()
        ),

        68 to Dialogue(
            text = "В бушующем море издалека раздавался рокот орудий. Суматоха боя... ",
            scene = scenes[5],
            options = listOf()
        ),

        69 to Dialogue(
            text = "РАДИОГРАММА (СУВОРОВ)::: Всем кораблям огонь прекратить.",
            scene = scenes[5],
            options = listOf()
        ),

        70 to Dialogue(
            text = "РАДИОГРАММА (СУВОРОВ)::: Всем кораблям о критических повреждениях должить.",
            scene = scenes[5],
            options = listOf()
        ),

        71 to Dialogue(
            text = "РАДИОГРАММА (СУВОРОВ)::: Всем кораблям следовать прежним курсом.",
            scene = scenes[5],
            options = listOf()
        ),

        72 to Dialogue(
            text = "Когда в Бресте собралась вся флотилия нам стало ясно, что никаких миноносцев не было... Идя по Доггерской Банке (так называют это богатое рыбой место) наша флотилия " +
                    "врезались в рыбацкие корабли. Наблюдатели со страху перепутали утлые судёнышки с миноносцами",
            scene = scenes[5],
            options = listOf()
        ),

        73 to Dialogue(
            text = "Неопытные наблюдатели, страх и последующая паника привели к тому, что эскадра расстреливала рыбацкие пароходики... Более того, когда часть кораблей включила боевое освещение, " +
                    "другие приняли их за корабли противника и открыли по ним огонь. Те в ответ открыли встречный огонь...",
            scene = scenes[5],
            options = listOf()
        ),

        74 to Dialogue(
            text = "20 минут эскадра расстреливала рыбацкие шхуны и свои собственные корабли... Больше всего в нашей флотилии досталось крейсеру АВРОРА В него попало 5 снарядов, которыми был смертельно " +
                    "ранен судовой иеромонах Анастасий Рукин. На корабле пробиты дымовая труба, машинный кожух и надводный борт в трёх местах. На броненосце ОРЕЛ разорвало во время выстрела " +
                    "дульную часть 75-мм орудия.",
            scene = scenes[5],
            options = listOf()
        ),

        75 to Dialogue(
            text = "На следующий день портовый город шумел об этом инциденте. В прессе я прочел, что новость уже стала известна по всей Европе, постреляли мы рыбаков и друг друга на " +
                    "потеху миру...",
            scene = scenes[5],
            options = listOf()
        ),

        76 to Dialogue(
            text = "The Times::: It is almost inconceivable that any men calling themselves seamen, however frightened they might be, could spend twenty minutes bombarding a fleet of " +
                    "fishing boats without discovering the nature of their target.",
            scene = scenes[5],
            options = listOf(
                Option(
                    text = "Следующая глава",
                    nextDialogueIndex = 100,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),
    )
}