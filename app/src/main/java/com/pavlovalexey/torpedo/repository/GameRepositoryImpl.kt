package com.pavlovalexey.torpedo.repository

/** основной блок кода сюжета Visual Novel "Torpedo Boat Grozny, содержащий в себе сцены, диалоги и прочие детали сюжета. Сюжет пишется только тут.*/

import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Option
import com.pavlovalexey.torpedo.model.Resource
import com.pavlovalexey.torpedo.model.Scene

class GameRepositoryImpl : GameRepository {

    /** определяем сцены по номерам. Каждый номер используется в определенном количестве диалогов, выбор происходит в методе ниже.*/
    private val scenes: List<Scene> = listOf(
        Scene(R.drawable.char0, "0"),
        Scene(R.drawable.scen_orel, "1"),
        Scene(R.drawable.scen_001, "2"),
        Scene(R.drawable.scen_002, "3"),
    )

    /** код ниже отвечает за выбор сцены для диалогов.
     * Например сцена 0 работает на диалоги с 0 по 5. И так далее. У каждого диалога как и у сцены свой индекс.*/
    override fun getSceneByDialogueIndex(dialogueIndex: Int): Scene {
        return if (dialogueIndex in 0..3) { // выбор режима сложности
            scenes[0]
        } else if (dialogueIndex in 4..9) { // подготовка к отплытию в Либаву
            scenes[1]
        } else if (dialogueIndex in 19..23) { // герой заходит на корабль
            scenes[2]
        } else if (dialogueIndex in 100..110) { // Торговец книгой (Капитал)
            scenes[3]
        } else {
            throw IllegalStateException("Scene not found for dialogue index: $dialogueIndex")
        }
    }

    /** получаем текущий диалог*/
    override fun getInitialDialogue(): Dialogue {
        return dialogues.firstOrNull()?.second
            ?: throw IllegalStateException("Доступных диалогов нет")
    }

    // Определение additionalResource как изменяемой переменной
    private var capital: Boolean = false
    private var necronomicon: Boolean = false

    // Функция для переключения additionalResource
    fun setAdditionalResource(value: Boolean) {
        capital = value
    }

    // Функция для получения диалогов с опциями на основе capital
    private fun getDialoguesWithOptions(): List<Pair<Int, Dialogue>> {
        return dialogues.map { (index, dialogue) ->
            index to dialogue.copy(
                options = if (capital && dialogue.capital) {
                    dialogue.options
                } else {
                    dialogue.options.filter { !it.capital }
                }
            )
        }
    }

    /** получаем индекс диалога*/
    override fun getDialogueByIndex(index: Int): Dialogue? {
        return getDialoguesWithOptions().find { it.first == index }?.second
    }

    /** получаем текущую сцену*/
    override fun getInitialScene(): Scene {
        return scenes.first()
    }

    /** ПЕРСОНАЖИ */
    private val bookseller = "Странный торговец"
    private val officer = "Офицер"
    private val novikov = "Новиков"

    /**
     * В этом коде реализована логика для управления сюжетом.
     * Основные компоненты кода:
     * Pair(0, Dialogue(                                 Пара (индекс диалога, объект диалога)
     **** text = "",                                     Текст диалога
     **** options = listOf(                              Опция диалога (список объектов типа опция, представляющий выбор из вариантов ответа (каждый объект вариант)
     ******* Option(                                     Объект вариант ответа
     ********** text = "",                               Текст варианта ответа
     ********** nextDialogueIndex = 1,                   Следующий диалог, при выборе этого варианта
     ********** resourceEffect = Resource(10, 10, 10)    Эффект на ресурсы (Рубли, слава, верность команды)
     */

    private val dialogues: List<Pair<Int, Dialogue>> = listOf(
        /** ГЛАВА 0 ПРОЛОГ*/
        Pair(
            0, Dialogue(
                text = "Выберите уровень сложности",
                options = listOf(
                    Option(
                        text = "Легкое испытание",
                        nextDialogueIndex = 1,
                        resourceEffect = Resource(0, 0, 0)
                    ),
                    Option(
                        text = "Трудный поход",
                        nextDialogueIndex = 2,
                        resourceEffect = Resource(0, 0, 0)
                    ),
                    Option(
                        text = "Стальная воля",
                        nextDialogueIndex = 3,
                        resourceEffect = Resource(0, 0, 0)
                    )
                )
            )
        ),
        Pair(
            1, Dialogue(
                text = "Начало игры в режиме Легкое испытание (Начальные ресурсы: 120%. Частота испытаний: 60%)",

                options = listOf(
                    Option(
                        text = "назад",
                        nextDialogueIndex = 0,
                        resourceEffect = Resource(0, 0, 0)
                    ),
                    Option(
                        text = "далее",
                        nextDialogueIndex = 4,
                        resourceEffect = Resource(4500, 6, 6)
                    )
                )
            )
        ),
        Pair(
            2, Dialogue(
                text = "Начало игры в режиме Трудный поход (Начальные ресурсы: 100%. Частота испытаний: 80%)",
                options = listOf(
                    Option(
                        text = "назад",
                        nextDialogueIndex = 0,
                        resourceEffect = Resource(0, 0, 0)
                    ),
                    Option(
                        text = "далее",
                        nextDialogueIndex = 4,
                        resourceEffect = Resource(3000, 5, 5)
                    )
                )
            )
        ),
        Pair(
            3, Dialogue(
                text = "Начало игры в режиме Стальная воля (Начальные ресурсы: 80%. Частота испытаний: 100%. Один слот для сохранений.)",
                options = listOf(
                    Option(
                        text = "назад",
                        nextDialogueIndex = 0,
                        resourceEffect = Resource(0, 0, 0)
                    ),
                    Option(
                        text = "далее",
                        nextDialogueIndex = 4,
                        resourceEffect = Resource(2400, 4, 4)
                    )
                )
            )
        ),
        Pair(
            4, Dialogue(
                text = "Лучики солнца проникают через занавеску, играя на моем лице...",
                options = listOf()
            )
        ),
        Pair(
            5, Dialogue(
                text = "За последние годы мне многим пришлось пожертвовать и еще большее сделать, чтобы сегодняшний день наступил.",
                options = listOf()
            )
        ),
        Pair(
            6, Dialogue(
                text = "В адмиралтействе я получили документы и приказ о назначении капитаном, ближайшим рейсом отправляюсь в порт Либава",
                options = listOf()
            )
        ),
        Pair(
            7, Dialogue(
                text = "В Либаве расквартирована Вторая Тихоокеанская эскадра и мой корабль - эскадренный миноносец Грозный",
                options = listOf()
            )
        ),
        Pair(
            8, Dialogue(
                text = "Корабль, на котором я иду на войну...",
                options = listOf()
            )
        ),
        Pair(
            9, Dialogue(
                text = "С друзьями и близкими я уже попрощался, теперь самыми близкими людьми на следующие пол года будет моя команда.",
                options = listOf()
            )
        ),

        /** ГЛАВА 1 ОТПЛЫТИЕ*/
        Pair(
            19, Dialogue(
                text = "** ГЛАВА 1 ОТПЛЫТИЕ **",
                options = listOf()
            )
        ),
        Pair(
            20, Dialogue(
                text = "Со мной в одном рейсе оказался Алексей Новиков, служащий за снабжение с эскадренного броненосца «Орёл», разговорчивый батлер поделился с мнением о команде, с которой мне придется управляться:",
                options = listOf()
            )
        ),
        Pair(
            21, Dialogue(
                text = "$novikov: Многие моряки на кораблях собраны из запаса. Эти старшие люди, неведомые к военно-морской службе, живут воспоминаниями о своей родине, болеют от разлуки с домом, с родными, с женою. Война обрушилась на них внезапно, как беда несмолкаемая, и они, готовясь к непривычному походу, выполняют свои обязанности с угнетенным видом...",
                options = listOf()
            )
        ),
        Pair(
            22, Dialogue(
                text = "$novikov: В число команды входит немало новобранцев. Измученные и жалкие, они взирают на все вокруг с застывшим ужасом в глазах. Их пугает море, на которое они попали впервые, а ещё больше — неизвестное будущее. Даже среди кадровых моряков, окончивших различные специальные школы, не видно обычного веселья...",
                options = listOf()
            )
        ),
        Pair(
            23, Dialogue(
                text = "$novikov: Только штрафные, в противоположность остальным, держатся более или менее бодро. Береговое начальство, чтобы отделаться от них, как от вредного элемента, придумало самый лёгкий способ: списывать их на суда, отправляющиеся на войну. Таким образом, к ужасу нашему, у нас набралось их до семи процентов...",
                options = listOf()
            )
        ),


        /** ГЛАВА 2 НЕОБЫЧНАЯ КНИГА: КАПИТАЛ*/
        Pair(
            100, Dialogue(
                text = "Решив прогуляться по городу вы я зашёл на местный рынок и через некоторое время ко мне подошёл мужчина вполне обыкновенного для местности вида, но вёл себя он странно - оглядывался так, как-будто хотел сделать что-то противозаконное",
                options = listOf()
            )
        ),
        Pair(
            101, Dialogue(
                text = "Офицер службы охраны, сопровождавший меня видимо сделал те же выводы, так как встал теперь между мной и этим сударем...",
                options = listOf()
            )
        ),
        Pair(
            102, Dialogue(
                text = "$bookseller: Не интересуют ли господ книги? У меня как раз есть один необычный экземпляр, вряд ли вы увидите такую у простых лавочников...",
                options = listOf()
            )
        ),
        Pair(
            103, Dialogue(
                text = "$officer: Ходебщик, так их у нас называют... Это букинисты, которые носят и предлагают к продаже книги",
                options = listOf()
            )
        ),
        Pair(
            104, Dialogue(
                text = "Заинтригованный, я прошу показать что за книгу он хочет предложить",
                options = listOf()
            )
        ),
        Pair(
            105, Dialogue(
                text = "Ходебщик разворачивает перекидной мешок, и достает толстую книгу, протягивая ее мне",
                options = if (capital) { // Проверяем значение
                    listOf(
                        Option(
                            text = "Взглянув на обложку, разочарованно понимаю, что эту книгу читал, и она не представляет интереса",
                            nextDialogueIndex = 110,
                            resourceEffect = Resource(0, 0, 0),
                        )
                    )
                } else {
                    listOf(
                        Option(
                            text = "На незнакомой обложке красуется название: Капитал. Автор книги: Карл Маркс...",
                            nextDialogueIndex = 106,
                            resourceEffect = Resource(0, 0, 0)
//                          necronomicon = true // Устанавливаем значение для этой опции
                        )
                    )
                },
            )
        ),
        Pair(
            106, Dialogue(
                text = "$bookseller: Скромные 100 Царских рублей господин! Уверяю вас, вы не пожалеете!",
                listOf(
                    Option(
                        text = "Купить книгу (ВНИМАНИЕ! Добавление книги в коллекцию может создать альтернативную историю развития событий)",
                        nextDialogueIndex = 109,
                        resourceEffect = Resource(-100, 0, 0),
                        capital = true // Устанавливаем значение для этой опции
                    ),
                    Option(
                        text = "Отказаться от покупки",
                        nextDialogueIndex = 110,
                        resourceEffect = Resource(0, 0, 0)
                    )
                )
            )
        ),
        Pair(
            109, Dialogue(
                text = "Расчитавшись с торговцем беру книгу из его дрожащих рук...",
                options = listOf()
            )
        ),
        Pair(
            110, Dialogue(
                text = "На рынке больше нечего смотреть и мне пора вернуться на корабль",
                options = listOf()
            )
        ),

//        Pair(
//            10, Dialogue(
//                text = "Какие будут первые распоряжения, капитан?",
//                options = listOf(
//                    Option(
//                        text = "Плывём грабить корованы!",
//                        nextDialogueIndex = 11,
//                        resourceEffect = Resource(2, 0, 0)
//                    ),
//                    Option(
//                        text = "Плывём поможем царевичу!",
//                        nextDialogueIndex = 11,
//                        resourceEffect = Resource(0, 2, 0)
//                    ),
//                    Option(
//                        text = "Пусть команда отдохнёт перед походом",
//                        nextDialogueIndex = 11,
//                        resourceEffect = Resource(0, 0, 2)
//                    )
//                )
//            )
//        ),



        /** ГЛАВА 10 */
        Pair(
            1000, Dialogue(
                text = "ГЛАВА 10. Конец игры",
                options = listOf()
            )
        ),
    )
}