package com.pavlovalexey.torpedo.repository.dialogues

/**
 * В этом коде реализована логика для управления сюжетом.
 * Основные компоненты кода:
 * 0 to Dialogue(                                         Пара (индекс диалога, объект диалога)
 * text = "",                                              Текст диалога // необязательный компонент
 * scene = scenes[0],                                       Сцена представляет собой подложку изображение // необязательный компонент
 * options = listOf(                                        Опция диалога (список объектов типа опция, представляющий выбор из вариантов ответа (каждый объект вариант)
 * Option(                                                     Объект вариант ответа
 * text = "",                                               Текст варианта ответа
 * nextDialogueIndex = 1,                                     Следующий диалог, при выборе этого варианта
 * resourceEffect = Resource(2000, 4, 0, 0, 0, 0, 0, 0, 0)    Эффект на ресурсы (Рубли, слава и тп) // необязательный компонент
 */

import com.pavlovalexey.torpedo.model.Characters
import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Option
import com.pavlovalexey.torpedo.model.Resource
import com.pavlovalexey.torpedo.model.Scene
import com.pavlovalexey.torpedo.model.Scenes



/** ГЛАВА 0 ПРОЛОГ*/

object Dialogue00 {

    internal val dialogues: List<Pair<Int, Dialogue>> = listOf(
        0 to Dialogue(
            text = "Санкт-Петербург. 1905 г.",
            scene = scenes[0], // Сцена 0 для диалога 0
            options = listOf(
                Option(
                    text = "Начать новую игру",
                    nextDialogueIndex = 1,
                    resourceEffect = Resource(2000, 4, 0, 0, 0, 0, 0, 0, 0),
                ),
            )
        ),

        1 to Dialogue(
            text = "Лучики солнца проникают через занавеску, играя на моем лице...",
            scene = scenes[1],
            options = listOf()
        ),

        2 to Dialogue(
            text = "За последние годы мне многим пришлось пожертвовать и еще большее сделать, чтобы сегодняшний день наступил.",
            options = listOf()
        ),

        3 to Dialogue(
            text = "В адмиралтействе я получили документы и приказ о назначении капитаном, ближайшим рейсом отправляюсь в порт Либава",
            options = listOf()
        ),

        4 to Dialogue(
            text = "В Либаве расквартирована Вторая Тихоокеанская эскадра и мой корабль - эскадренный миноносец Грозный",
            options = listOf()
        ),

        5 to Dialogue(
            text = "Корабль, на котором я иду на войну...",
            scene = scenes[1],
            options = listOf()
        ),

        6 to Dialogue(
            text = "Пора пращаться с благоверной невестой...",
            scene = scenes[1],
            options = listOf()
        ),

        7 to Dialogue(
            text = "${Characters.daria}::: Почему на войну идешь именно ты?! Почему ни кто-то другой?!",
            scene = scenes[10],
            options = listOf()
        ),

        8 to Dialogue(
            text = "Разговор общает быть не простым... Стараюсь сохранить спокойствие и сгладить углы перед прощанием...",
            options = listOf()
        ),

        9 to Dialogue(
            text = " - Потому-что это мой долг, дорогая... Твой жених офицер флота... \n\n" +
                    "А кроме того, благодаря этому назначению я стал еще и капитаном корабля, КАПИТАНОМ, ты понимаешь? ",
            options = listOf()
        ),

        10 to Dialogue(
            text = "${Characters.daria}::: И что же мне теперь, быть вдовой капитана и радоваться этому?",
            options = listOf(
                Option(
                    text = "Такова твоя женская доля, милая",
                    nextDialogueIndex = 11,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, -1),
                ),
                Option(
                    text = "Ты будешь радоваться моим успехом и праздновать вместе со мной победу!",
                    nextDialogueIndex = 12
                ),
                Option(
                    text = "Клянусь тебе, душа моя, я ни дня не перестану думать о тебе в походе! И даже ночью ты будешь приходить ко мне во снах",
                    nextDialogueIndex = 13,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 1),
                )
            ),
        ),
        11 to Dialogue(
            text = "${Characters.daria}::: Спасибо, что напомнил мне об этом, свет очей моих... что еще скажешь на прощание?",
            options = listOf(
                Option(
                    text = "Думаю мне пора, возможно свидимся, дорогая...",
                    nextDialogueIndex = 14,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, -1),
                ),
                Option(
                    text = "Ты будешь радоваться моим успехом и праздновать вместе со мной победу!",
                    nextDialogueIndex = 15
                ),
                Option(
                    text = "Клянусь тебе, душа моя, я ни дня не перестану думать о тебе в походе! И даже ночью ты будешь приходить ко мне во снах",
                    nextDialogueIndex = 16,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 1),
                )
            ),
        ),
        12 to Dialogue(
            text = "${Characters.daria}::: Надеюсь победа не будет стоить тебе жизни...",
            options = listOf(
                Option(
                    text = "Думаю мне пора, возможно свидимся, дорогая...",
                    nextDialogueIndex = 15,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, -1),
                ),
                Option(
                    text = "Я привезу тебе заморских сувениров, возможно красивое шёлковое кимоно!",
                    nextDialogueIndex = 16
                ),
                Option(
                    text = "Клянусь тебе, душа моя, я ни дня не перестану думать о тебе в походе! И даже ночью ты будешь приходить ко мне во снах",
                    nextDialogueIndex = 17,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 1),
                )
            ),
        ),
        13 to Dialogue(
            text = "Благоверная покраснела...",
            options = listOf(
                Option(
                    text = "Думаю мне пора, возможно свидимся, дорогая...",
                    nextDialogueIndex = 16,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, -1),
                ),
                Option(
                    text = "Ты будешь радоваться моим успехом и праздновать вместе со мной победу!",
                    nextDialogueIndex = 17,
                ),
                Option(
                    text = "Благодаря твоей горячей любви я смогу выбраться из пекла войны и вернуться к тебе живым, и мы отпразнуем достойную свадьбу!",
                    nextDialogueIndex = 18,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 1),
                )
            ),
        ),
        14 to Dialogue(
            text = "Категорически согластна с тем, что тебе пора...",
            scene = scenes[10],
            options = listOf(
                Option(
                    text = "С чувством глубокой горечи покидаю дом Дарьи и направляюсь в кабак - утоплю эту горечь там в бокале и за игральным столом",
                    nextDialogueIndex = 19,
                    resourceEffect = Resource(-300, 0, 0, 0, 0, 0, 0, 0, 2)
                )
            )
        ),
        15 to Dialogue(
            text = "Думаю, тебе пора...",
            scene = scenes[10],
            options = listOf(
                Option(
                    text = "С чувством горечи покидаю дом Дарьи и направляюсь в кабак - утоплю эту горечь там в бокале",
                    nextDialogueIndex = 19,
                    resourceEffect = Resource(-100, 0, 0, 0, 0, 0, 0, 0, 1)
                )
            )
        ),
        16 to Dialogue(
            text = "Надеюсь ты вернешься живым...",
            scene = scenes[10],
            options = listOf(
                Option(
                    text = "Со смешенными чувствами покидаю дом Дарьи и возвращаюсь домой",
                    nextDialogueIndex = 19,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),
        17 to Dialogue(
            text = "Благоверная меня обнимает и после ужина мы расстаемся.",
            scene = scenes[10],
            options = listOf(
                Option(
                    text = "С чувством покоя возвращаюсь домой",
                    nextDialogueIndex = 19,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, -1)
                )
            )
        ),
        18 to Dialogue(
            text = "Благоверная меня обнимает и после ужина мы с трудом расстаемся. В слезах она обещает, что за меня словечко батеньке адмиралу...",
            scene = scenes[10],
            options = listOf(
                Option(
                    text = "Начать следующую главу",
                    nextDialogueIndex = 19, // Индекс первого диалога из второго файла
                    resourceEffect = Resource(0, 1, 0, 0, 0, 0, 0, 0, -2) // Эффект на ресурсы (если нужно)
                )
            )
        ),
    )
}
