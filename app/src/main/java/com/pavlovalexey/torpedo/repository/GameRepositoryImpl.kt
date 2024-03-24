package com.pavlovalexey.torpedo.repository

/** основной блок кода сюжета Visual Novel "Torpedo Boat Grozny, содержащий в себе сцены, диалоги и прочие детали сюжета. Сюжет пишется только тут.*/

import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.model.Characters.admiral
import com.pavlovalexey.torpedo.model.Characters.bookseller
import com.pavlovalexey.torpedo.model.Characters.novikov
import com.pavlovalexey.torpedo.model.Characters.paramonov
import com.pavlovalexey.torpedo.model.Characters.reztsov
import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Option
import com.pavlovalexey.torpedo.model.Resource
import com.pavlovalexey.torpedo.model.Scene

class GameRepositoryImpl : GameRepository {

    /** определяем сцены по номерам. Каждый номер используется в определенном количестве диалогов, выбор происходит в методе ниже.*/
    private val scenes: List<Scene> = listOf(
        Scene(R.drawable.scen_spb1, "0"),
        Scene(R.drawable.scen_spb2, "1"),
        Scene(R.drawable.scen_ships03, "2"),
        Scene(R.drawable.scen_ships06, "3"),
        Scene(R.drawable.scen_ships04, "4"),
    )

    /** код ниже отвечает за выбор сцены для диалогов.
     * Например сцена 0 работает на диалоги с 0 по 5. И так далее. У каждого диалога как и у сцены свой индекс.*/
//    override fun getSceneByDialogueIndex(dialogueIndex: Int): Scene {
//        return if (dialogueIndex in 0..3) { // выбор режима сложности
//            scenes[0]
//        } else if (dialogueIndex in 4..9) { // подготовка к отплытию в Либаву
//            scenes[1]
//        } else if (dialogueIndex in 19..23) { // герой заходит на корабль
//            scenes[2]
//        } else if (dialogueIndex in 100..110) { // Торговец книгой (Капитал)
//            scenes[3]
//        } else {
//            throw IllegalStateException("Scene not found for dialogue index: $dialogueIndex")
//        }
//    }

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
                options = if (capital) {
                    dialogue.options
                } else {
                    dialogue.options.filter { !it.NotUsingThisCaseIfCapitalIsTrue }
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

//    /** ПЕРСОНАЖИ */
//    private val bookseller = "Странный торговец"
//    private val officer = "Офицер"
//    private val novikov = "Новиков"
//    private val admiral = "Адмирал Рожественский"

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
                scene = scenes[0],
                options = listOf(
                    Option(
                        text = "Легкое испытание",
                        nextDialogueIndex = 1,
                        resourceEffect = Resource(0, 0, 0, 0, 0)
                    ),
                    Option(
                        text = "Трудный поход",
                        nextDialogueIndex = 2,
                        resourceEffect = Resource(0, 0, 0, 0, 0)
                    ),
                    Option(
                        text = "Стальная воля",
                        nextDialogueIndex = 3,
                        resourceEffect = Resource(0, 0, 0, 0, 0)
                    )
                )
            )
        ),
        Pair(
            1, Dialogue(
                text = "Начало игры в режиме Легкое испытание (Начальные ресурсы: 120%. Частота испытаний: 60%)",
                scene = scenes[0],
                options = listOf(
                    Option(
                        text = "назад",
                        nextDialogueIndex = 0,
                        resourceEffect = Resource(0, 0, 0, 0, 0)
                    ),
                    Option(
                        text = "далее",
                        nextDialogueIndex = 4,
                        resourceEffect = Resource(4500, 6, 6, 12, 12)
                    )
                )
            )
        ),
        Pair(
            2, Dialogue(
                text = "Начало игры в режиме Трудный поход (Начальные ресурсы: 100%. Частота испытаний: 80%)",
                scene = scenes[0],
                options = listOf(
                    Option(
                        text = "назад",
                        nextDialogueIndex = 0,
                        resourceEffect = Resource(0, 0, 0, 0, 0)
                    ),
                    Option(
                        text = "далее",
                        nextDialogueIndex = 4,
                        resourceEffect = Resource(3000, 5, 5, 10, 10)
                    )
                )
            )
        ),
        Pair(
            3, Dialogue(
                text = "Начало игры в режиме Стальная воля (Начальные ресурсы: 80%. Частота испытаний: 100%. Один слот для сохранений.)",
                scene = scenes[0],
                options = listOf(
                    Option(
                        text = "назад",
                        nextDialogueIndex = 0,
                        resourceEffect = Resource(0, 0, 0, 0, 0)
                    ),
                    Option(
                        text = "далее",
                        nextDialogueIndex = 4,
                        resourceEffect = Resource(2400, 4, 4, 8, 8)
                    )
                )
            )
        ),
        Pair(
            4, Dialogue(
                text = "Лучики солнца проникают через занавеску, играя на моем лице...",
                scene = scenes[1],
                options = listOf()
            )
        ),
        Pair(
            5, Dialogue(
                text = "За последние годы мне многим пришлось пожертвовать и еще большее сделать, чтобы сегодняшний день наступил.",
                scene = scenes[1],
                options = listOf()
            )
        ),
        Pair(
            6, Dialogue(
                text = "В адмиралтействе я получили документы и приказ о назначении капитаном, ближайшим рейсом отправляюсь в порт Либава",
                scene = scenes[1],
                options = listOf()
            )
        ),
        Pair(
            7, Dialogue(
                text = "В Либаве расквартирована Вторая Тихоокеанская эскадра и мой корабль - эскадренный миноносец Грозный",
                scene = scenes[1],
                options = listOf()
            )
        ),
        Pair(
            8, Dialogue(
                text = "Корабль, на котором я иду на войну...",
                scene = scenes[1],
                options = listOf()
            )
        ),
        Pair(
            9, Dialogue(
                text = "С друзьями и близкими я уже попрощался, теперь самыми близкими людьми на следующие пол года будет моя команда.",
                scene = scenes[1],
                options = listOf(
                    Option(
                        text = "Пора...",
                        nextDialogueIndex = 19,
                        resourceEffect = Resource(0, 0, 0, 0, 0)
                    )
                )
            )
        ),

        /** ГЛАВА 1 ОТПЛЫТИЕ*/
        Pair(
            19, Dialogue(
                text = "** ГЛАВА 1 ОТПЛЫТИЕ **",
                scene = scenes[2],
                options = listOf()
            )
        ),
        Pair(
            20, Dialogue(
                text = "Со мной в одном рейсе оказался Алексей Новиков, служащий за снабжение с эскадренного броненосца «Орёл», разговорчивый батлер поделился с мнением о команде, с которой мне придется управляться:",
                scene = scenes[2],
                options = listOf()
            )
        ),
        Pair(
            21, Dialogue(
                text = "$novikov: Многие моряки на кораблях собраны из запаса. Эти старшие люди, неведомые к военно-морской службе, живут воспоминаниями о своей родине, болеют от разлуки с домом, с родными, с женою. Война обрушилась на них внезапно, как беда несмолкаемая, и они, готовясь к непривычному походу, выполняют свои обязанности с угнетенным видом...",
                scene = scenes[2],
                options = listOf()
            )
        ),
        Pair(
            22, Dialogue(
                text = "$novikov: В число команды входит немало новобранцев. Измученные и жалкие, они взирают на все вокруг с застывшим ужасом в глазах. Их пугает море, на которое они попали впервые, а ещё больше — неизвестное будущее. Даже среди кадровых моряков, окончивших различные специальные школы, не видно обычного веселья...",
                scene = scenes[2],
                options = listOf()
            )
        ),
        Pair(
            23, Dialogue(
                text = "$novikov: Только штрафные, в противоположность остальным, держатся более или менее бодро. Береговое начальство, чтобы отделаться от них, как от вредного элемента, придумало самый лёгкий способ: списывать их на суда, отправляющиеся на войну. Таким образом, к ужасу нашему, у нас набралось их до семи процентов...",
                scene = scenes[2],
                options = listOf()
            )
        ),
        Pair(
            24, Dialogue(
                text = "Удручающая ситуация...",
                scene = scenes[2],
                options = listOf()
            )
        ),
        Pair(
            25, Dialogue(
                text = "Добравшись до Либавы мы с Новиковым направляемся в комментатуру флота доложить о прибытии. В коммендатуре все стоят на ушах - вот-вот флот должен выдвинуться в поход и суета наполнила портовый город",
                scene = scenes[2],
                options = listOf()
            )
        ),
        Pair(
            26, Dialogue(
                text = "Рожественский Зиновий Петрович - начальник штаба флота вскоре провёл обращение к командирам и старшим офицерам флота, на которое явился и я. Завтра этот мужчина из начальника штаба станет вице-адмиралом флота...",
                scene = scenes[2],
                options = listOf()
            )
        ),
        Pair(
            27, Dialogue(
                text = "$admiral: На походе ночью не позволять никакому судну пересекать путь эскадры и приближаться к ней на расстояние менее 4 кабельтовых. Выстрелом под нос остановить приближающееся судно, указать ему курс выхода из запретной зоны или ожидать, пока эскадра не пройдёт. В случае невыполнения судном требований, применять по нему все виды оружия. Суда, не мешающие эскадре, освещать прожектором",
                scene = scenes[2],
                options = listOf()
            )
        ),
        Pair(
            28, Dialogue(
                text = "Кроме технических деталей похода, Рождественский пытался вдохновить офицеров перед операцией, которая вызывала беспокойство практически у всех, ведь мы идем на защиту крепости Порт-Артур, которая к моменту нашего пребытия вероятно уже падёт...",
                scene = scenes[2],
                options = listOf()
            )
        ),
        Pair(
            29, Dialogue(
                text = "И наш недавно собранный наспех флот встретится с японцами лицом к лицу на их территории. У врага будет преимущество в силе, снабжении... Об этом Рождественский конечно не упоминал - целью его обращения было вдохновить нас, а не наоборот",
                scene = scenes[2],
                options = listOf()
            )
        ),
        Pair(
            30, Dialogue(
                text = "После речи начальник штаба спешно отбыл, а я не имел шанса поприветствовать его лично. Сегодня я лишь переговорил с несколькими знакомыми офицерами и прибыл на свой корабль",
                scene = scenes[2],
                options = listOf()
            )
        ),
        Pair(
            31, Dialogue(
                text = "Прекрасный миноносец, недавно покрытый свежей краской дожидался своего командира и командир взошёл на борт",
                scene = scenes[3],
                options = listOf()
            )
        ),
        Pair(
            32, Dialogue(
                text = "$paramonov: Капитан 2-го ранга Парамонов - Офицер артилерии",
                scene = scenes[3],
                options = listOf()
            )
        ),
        Pair(
            33, Dialogue(
                text = "$paramonov: В отсутствии капитана 1-го ранка на корабле, ответственность за миноносец лежит на мне. Покажите ваши документы, полковник!",
                scene = scenes[3],
                options = listOf()
            )
        ),
        Pair(
            34, Dialogue(
                text = "Старший офицер на корабле проверил мои документы и судя по лицу он удовлетворён.",
                scene = scenes[3],
                options = listOf()
            )
        ),
        Pair(
            35, Dialogue(
                text = "$paramonov: Приветствую Вас на корабле, капитан!",
                scene = scenes[3],
                options = listOf()
            )
        ),
        Pair(
            36, Dialogue(
                text = "$paramonov: Вы можете видеть сейчас на палубе весь офицерский состав корабля:",
                scene = scenes[3],
                options = listOf()
            )
        ),
        Pair(
            37, Dialogue(
                text = "$paramonov: Капитан 2-го ранга Алексеев - офицер торпедного вооружения. Капитан лейтенант Ибрагимов - навигационный офицер. Капитан-поручик Магометов и капитан-поручик Феодосов - офицеры торпедного вооружения. Капитан-поручик Шмель - офицер связи. Капитан-пороучик Резцов - офицер разведки.",
                scene = scenes[3],
                options = listOf()
            )
        ),
        Pair(
            38, Dialogue(
                text = "$paramonov: Подпоручик Гай - инженерные и ходовые системы. Карабельный врач Бухарин. К сожалению корабль недостаточно укомплектован механиками, инженерами и священником, а количество матросов на 10% ниже штатной численности. Но приказ адмирала о готовности корабля к походу поступил.",
                scene = scenes[3],
                options = listOf()
            )
        ),
        Pair(
            39, Dialogue(
                text = "$paramonov: Какие будут распоряжения, капитан?",
                scene = scenes[3],
                options = listOf(
                    Option(
                        text = "Проведем ревизию запасов и вооружения",
                        nextDialogueIndex = 40,
                        resourceEffect = Resource(0, 0, -1, -1, -1)
                    ),
                    Option(
                        text = "Всем отбой. Отдыхаем перед походом",
                        nextDialogueIndex = 41,
                        resourceEffect = Resource(0, 0, 1, 0, 0)
                    ),
                    Option(
                        text = "Выставить на ужин ендовы с водкой для команды, удвоить мерную кружку. Взять в кабаке бочку офицерам из лучших запасов.",
                        nextDialogueIndex = 41,
                        resourceEffect = Resource(-100, 1, 2, -1, 0)
                    )
                )
            )
        ),
        Pair(
            40, Dialogue(
                text = "Ревизия затянулась до полуночи, а результат ревизии огорчил меня трижды. Во-первых ревизия обнаружила недостачу тяжелого автоматического оружия, во-вторых недостачу водки для матросов, а в третьих - команда недовольна как тем, что вместо отдыха перед походом пришлось заниматься работой, так и тем, что обнаружилась кража водки. Одно радует - что недостача обнаружилась сейчас, а не в середине похода и мы сможем восполнить ее при первой возможности",
                scene = scenes[3],
                options = listOf()
            )
        ),
        Pair(
            41, Dialogue(
                text = "3 ноября 1904 года. Спал я тревожно и сон долго не шёл. Мне приснилось, как Грозный весь в пробоинах, со страшным креном и раненными матросами, лежащими на палубе на полном ходу уходит от попятам приследующего врага... Я встрехнул голову, прогоняя навождение и возвращаясь в реальность. Слышно, как гудят паровые машины, команды с разных кораблей флотилии, Вторая тихоокеанская эскадра просыпается и выдвигается в поход...",
                scene = scenes[3],
                options = listOf(
                    Option(
                        text = "Следующая глава",
                        nextDialogueIndex = 100,
                        resourceEffect = Resource(0, 0, 0, 0, 0)
                    )
                )
            )
        ),

        /** ГЛАВА 3 НЕОБЫЧНАЯ КНИГА: КАПИТАЛ*/
        Pair(
            100, Dialogue(
                text = "Решив прогуляться по городу я зашёл на местный рынок и через некоторое время ко мне подошёл мужчина вполне обыкновенного для местности вида, но вёл себя он странно - оглядывался так, как-будто хотел сделать что-то противозаконное",
                scene = scenes[4],
                options = listOf()
            )
        ),
        Pair(
            101, Dialogue(
                text = "Капитан-пороучик Резцов, сопровождавший меня видимо сделал те же выводы, так как встал теперь между мной и этим сударем...",
                scene = scenes[4],
                options = listOf()
            )
        ),
        Pair(
            102, Dialogue(
                text = "$bookseller: Не интересуют ли господ книги? У меня как раз есть один необычный экземпляр, вряд ли вы увидите такую у простых лавочников...",
                scene = scenes[4],
                options = listOf()
            )
        ),
        Pair(
            103, Dialogue(
                text = "$reztsov: Ходебщик, так их у нас называют... Это букинисты, которые носят и предлагают к продаже книги",
                scene = scenes[4],
                options = listOf()
            )
        ),
        Pair(
            104, Dialogue(
                text = "Заинтригованный, я прошу показать что за книгу он хочет предложить",
                scene = scenes[4],
                options = listOf()
            )
        ),
        Pair(
            105, Dialogue(
                text = "Ходебщик разворачивает перекидной мешок, и достает толстую книгу, протягивая ее мне",
                scene = scenes[4],
                options = if (capital) { // Проверяем значение
                listOf(
                    Option(
                        text = "Взглянув на обложку, разочарованно понимаю, что эту книгу читал, и она не представляет интереса",
                        nextDialogueIndex = 110,
                        resourceEffect = Resource(0, 0, 0, 0, 0),
                    ),
                    )
                } else {
                    listOf(
                    Option(
                        text = "На незнакомой обложке красуется название: Капитал. Автор книги: Карл Маркс...",
                        nextDialogueIndex = 106,
                        resourceEffect = Resource(0, 0, 0, 0, 0),
//                        NotUsingThisCaseIfCapitalIsTrue = true // Устанавливаем значение, блокирующее этот вариант ответа для случая, если книга Капитал уже имеется
                    )
                )
                },
            )
        ),
        Pair(
            106, Dialogue(
                text = "$bookseller: Скромные 100 Царских рублей господин! Уверяю вас, вы не пожалеете!",
                scene = scenes[4],
                listOf(
                    Option(
                        text = "Купить книгу (ВНИМАНИЕ! Добавление книги в коллекцию может создать альтернативную историю развития событий)",
                        nextDialogueIndex = 109,
                        resourceEffect = Resource(-100, 0, 0, 0, 0, true),
//                        capital = true // Устанавливаем значение для этой опции
                    ),
                    Option(
                        text = "Отказаться от покупки",
                        nextDialogueIndex = 110,
                        resourceEffect = Resource(0, 0, 0, 0, 0),
                    )
                )
            )
        ),
        Pair(
            109, Dialogue(
                text = "Расчитавшись с торговцем беру книгу из его дрожащих рук...",
                scene = scenes[4],
                options = listOf()
            )
        ),
        Pair(
            110, Dialogue(
                text = "На рынке больше нечего смотреть и мне пора вернуться на корабль",
                scene = scenes[4],
                options = listOf()
            )
        ),

        /** ГЛАВА 10 */
        Pair(
            1000, Dialogue(
                text = "ГЛАВА 10. Конец игры",
                scene = scenes[4],
                options = listOf()
            )
        ),
    )
}