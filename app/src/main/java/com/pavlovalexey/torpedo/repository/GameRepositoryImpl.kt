package com.pavlovalexey.torpedo.repository

/** основной блок кода сюжета Visual Novel "Torpedo Boat Grozny, содержащий в себе сцены, диалоги и прочие детали сюжета. Сюжет пишется только тут.*/

import android.content.Context
import android.content.res.Resources
import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.model.Characters.admiral
import com.pavlovalexey.torpedo.model.Characters.anastasia
import com.pavlovalexey.torpedo.model.Characters.bookseller
import com.pavlovalexey.torpedo.model.Characters.paramonov
import com.pavlovalexey.torpedo.model.Characters.reztsov
import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Option
import com.pavlovalexey.torpedo.model.Resource
import com.pavlovalexey.torpedo.model.Scene
import com.pavlovalexey.torpedo.model.Characters.bumblebee
import com.pavlovalexey.torpedo.model.Characters.next
import com.pavlovalexey.torpedo.model.Characters.novikov

class GameRepositoryImpl(private val context: Context) : GameRepository {

    private var relationship: Int = 0
    private var capital: Int = 0
    private var necronomicon: Int = 0
    val bookText = context.getString(R.string.kapital)
    private var lastReadFragment: String = ""
    var currentBookPosition: Int = 300

    /** определяем сцены по номерам. Каждый номер используется в определенном количестве диалогов, выбор происходит в методе ниже.*/
    private val scenes: List<Scene> = listOf(
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
        Scene(R.drawable.lady01, "11"), // Благоверная невеста
    )

    /** получаем текущий диалог*/
    override fun getInitialDialogue(): Dialogue {
        return dialogues.firstOrNull()?.second
            ?: throw IllegalStateException("Доступных диалогов нет")
    }

    // Функция для получения диалогов с опциями на основе capital
    private fun getDialoguesWithOptions(): List<Pair<Int, Dialogue>> {
        return dialogues.map { (index, dialogue) ->
            index to dialogue.copy(
                options = dialogue.options
            )
        }
    }

    /** получаем индекс диалога*/
    override fun getDialogueByIndex(index: Int): Dialogue? {
        val dialogue = getDialoguesWithOptions().find { it.first == index }?.second
        dialogue?.let {
            if (index == 112 || index == 560) {
                /** отмечаем диалоги, в которых будет чтение книги*/
                val nextFragment = getNextBookFragment()
                updateDialogueWithNextFragment(it, nextFragment, 0, 0)
            }
        }
        return dialogue
    }

    /** получаем текущую сцену*/
    override fun getInitialScene(): Scene {
        return scenes.first()
    }

    fun getNextBookFragment(): String {
        currentBookPosition += 100 // Используем фиксированное значение для увеличения позиции чтения
        val endIndex = currentBookPosition + 300 // Изменяем конечную позицию чтения
        lastReadFragment = if (currentBookPosition < bookText.length) {
            if (endIndex < bookText.length) {
                bookText.substring(currentBookPosition, endIndex)
            } else {
                bookText.substring(currentBookPosition)
            }
        } else {
            "Конец книги"
        }
        return lastReadFragment // Возвращаем последний прочитанный фрагмент книги
    }

    // Функция для обновления текста диалога на основе последнего прочитанного фрагмента книги
    fun updateDialogueWithNextFragment(dialogue: Dialogue, nextFragment: String, relationshipEffect: Int = 0, capitalEffect: Int = 0) {
        dialogue.text = nextFragment
        relationship += relationshipEffect
        capital += capitalEffect
    }

    /**
     * В этом коде реализована логика для управления сюжетом.
     * Основные компоненты кода:
     * Pair(0 to Dialogue(                                 Пара (индекс диалога, объект диалога)
     **** text = "",                                     Текст диалога
     **** options = listOf(                              Опция диалога (список объектов типа опция, представляющий выбор из вариантов ответа (каждый объект вариант)
     ******* Option(                                     Объект вариант ответа
     ********** text = "",                               Текст варианта ответа
     ********** nextDialogueIndex = 1,                   Следующий диалог, при выборе этого варианта
     ********** resourceEffect = Resource(10, 10, 10)    Эффект на ресурсы (Рубли, слава, верность команды)
     */


    private val dialogues: List<Pair<Int, Dialogue>> = listOf(

        /** ГЛАВА 0 ПРОЛОГ*/

        0 to Dialogue(
            text = "Выберите уровень сложности",
            scene = scenes[0],
            options = listOf(
                Option(
                    text = "Легкое испытание",
                    nextDialogueIndex = 1
                ),
                Option(
                    text = "Трудный поход",
                    nextDialogueIndex = 2
                ),
                Option(
                    text = "Стальная воля",
                    nextDialogueIndex = 3
                )
            )
        ),
        1 to Dialogue(
            text = "Начало игры в режиме Легкое испытание (Начальные ресурсы: 120%. Частота испытаний: 60%)",
            scene = scenes[0],
            options = listOf(
                Option(
                    text = "назад",
                    nextDialogueIndex = 0
                ),
                Option(
                    text = "далее",
                    nextDialogueIndex = 4,
                    resourceEffect = Resource(4500, 6, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),

        2 to Dialogue(
            text = "Начало игры в режиме Трудный поход (Начальные ресурсы: 100%. Частота испытаний: 80%)",
            scene = scenes[0],
            options = listOf(
                Option(
                    text = "назад",
                    nextDialogueIndex = 0
                ),
                Option(
                    text = "далее",
                    nextDialogueIndex = 4,
                    resourceEffect = Resource(3000, 5, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),

        3 to Dialogue(
            text = "Начало игры в режиме Стальная воля (Начальные ресурсы: 80%. Частота испытаний: 100%. Один слот для сохранений.)",
            scene = scenes[0],
            options = listOf(
                Option(
                    text = "назад",
                    nextDialogueIndex = 0
                ),
                Option(
                    text = "далее",
                    nextDialogueIndex = 4,
                    resourceEffect = Resource(2400, 4, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),

        4 to Dialogue(
            text = "Лучики солнца проникают через занавеску, играя на моем лице...",
            scene = scenes[1],
            options = listOf()
        ),

        5 to Dialogue(
            text = "За последние годы мне многим пришлось пожертвовать и еще большее сделать, чтобы сегодняшний день наступил.",
            scene = scenes[1],
            options = listOf()
        ),

        6 to Dialogue(
            text = "В адмиралтействе я получили документы и приказ о назначении капитаном, ближайшим рейсом отправляюсь в порт Либава",
            scene = scenes[1],
            options = listOf()
        ),

        7 to Dialogue(
            text = "В Либаве расквартирована Вторая Тихоокеанская эскадра и мой корабль - эскадренный миноносец Грозный",
            scene = scenes[1],
            options = listOf()
        ),

        8 to Dialogue(
            text = "Корабль, на котором я иду на войну...",
            scene = scenes[1],
            options = listOf()
        ),

        9 to Dialogue(
            text = "Пора пращаться с благоверной невестой...",
            scene = scenes[1],
            options = listOf()
        ),

        10 to Dialogue(
            text = "$anastasia::: Почему на войну идешь именно ты?! Почему ни кто-то другой?!",
            scene = scenes[10],
            options = listOf()
        ),

        11 to Dialogue(
            text = "Потому-что это мой долг, я офицер флота. \n\n" +
                    "А кроме того, благодаря этому назначению я стану капитаном корабля, КАПИТАНОМ, ты понимаешь? ",
            scene = scenes[10],
            options = listOf()
        ),

        12 to Dialogue(
            text = "$anastasia::: И что же мне теперь, быть вдовой капитана и радоваться этому?",
            scene = scenes[10],
            options = listOf(
                Option(
                    text = "Такова твоя женская доля, милая",
                    nextDialogueIndex = 13,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, -1),
                ),
                Option(
                    text = "Ты будешь радоваться моим успехом и праздновать вместе со мной победу!",
                    nextDialogueIndex = 13
                ),
                Option(
                    text = "Клянусь тебе, душа моя, я ни дня не перестану думать о тебе в походе! И даже ночью ты будешь приходить ко мне во снах",
                    nextDialogueIndex = 13,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 1),
                )
            ),
            dialogFunction = { relationship + 1 }
        ),

        13 to Dialogue(
            text = if (relationship < 0) {
                "$anastasia::: Спасибо, что напомнил мне об этом, свет очей моих... что еще скажешь на прощание?"
            } else if (relationship == 0) {
                "$anastasia::: Надеюсь победа не будет стоить тебе жизни..."
            } else if (relationship > 0) {
                "Благоверная покраснела, кажется её решимость переубедить меня колеблется"
            } else {
                "$anastasia::: ..."
            },
            scene = scenes[10],
            options = listOf(
                Option(
                    text = "Думаю мне пора, возможно свидимся, Настенька...",
                    nextDialogueIndex = 14,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, -1),
                ),
                Option(
                    text = "Я привезу тебе заморских сувениров, возможно красивое шёлковое кимоно",
                    nextDialogueIndex = 14,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 1),
                ),
                Option(
                    text = "Благодаря твоей горячей любви я смогу выбраться из пекла войны и вернуться к тебе живым, " +
                            "и мы отпразнуем достойную свадьбу!",
                    nextDialogueIndex = 14,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 1),
                )
            ),
            dialogFunction = { relationship + 1 }
        ),

        14 to Dialogue(
            text = when (relationship) {
                -2 -> "$anastasia::: Категорически согластна с тем, что тебе пора... "
                -1 -> "$anastasia::: Думаю, тебе пора..."
                0 -> "$anastasia::: ..."
                1 -> "Благоверная меня обнимает и после ужина мы расстаемся."
                2 -> "Благоверная меня обнимает и после ужина мы расстаемся. Она обещает, что найдет способ отправить мне весточку"
                else -> "$anastasia::: ..."
            },
            scene = scenes[10],
            options = listOf(
                when (relationship) {
                    -2 -> Option(
                        text = "С чувством глубокой горечи покидаю дом Насти и направляюсь в кабак - утоплю эту горечь там в бокале и за игральным столом",
                        nextDialogueIndex = 18,
                        resourceEffect = Resource(-300, 0, 0, 0, 0, 0, 0, 0, 2)
                    )

                    -1 -> Option(
                        text = "С чувством горечи покидаю дом Насти и направляюсь в кабак - утоплю эту горечь там в бокале",
                        nextDialogueIndex = 18,
                        resourceEffect = Resource(-100, 0, 0, 0, 0, 0, 0, 0, 1)
                    )

                    0 -> Option(
                        text = "Покидаю дом Насти и возвращаюсь домой",
                        nextDialogueIndex = 18,
                        resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                    )

                    1 -> Option(
                        text = "С чувством покоя возвращаюсь домой",
                        nextDialogueIndex = 18,
                        resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, -1)
                    )

                    2 -> Option(
                        text = "С чувством покоя возвращаюсь домой",
                        nextDialogueIndex = 18,
                        resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, -2)
                    )

                    else -> Option(
                        text = "...",
                        nextDialogueIndex = 18
                    )
                }
            )
        ),

        18 to Dialogue(
            text = "Покидаю Петербург... $relationship",
            scene = scenes[0],
            options = listOf(
                Option(
                    text = next,
                    nextDialogueIndex = 19
                )
            )
        ),


        /** ГЛАВА 1 ОТПЛЫТИЕ*/

        19 to Dialogue(
            text = "** ОТПЛЫТИЕ **",
            scene = scenes[2],
            options = listOf()
        ),

        20 to Dialogue(
            text = "Со мной в одном рейсе оказался Алексей Новиков, служащий за снабжение с эскадренного броненосца «Орёл», разговорчивый батлер поделился с мнением о команде, с которой мне придется управляться:",
            scene = scenes[2],
            options = listOf()
        ),

        21 to Dialogue(
            text = "$novikov::: Многие моряки на кораблях собраны из запаса. Эти старшие люди, неведомые к военно-морской службе, живут воспоминаниями о своей родине, болеют от разлуки с домом, с родными, с женою. Война обрушилась на них внезапно, как беда несмолкаемая, и они, готовясь к непривычному походу, выполняют свои обязанности с угнетенным видом...",
            scene = scenes[2],
            options = listOf()
        ),

        22 to Dialogue(
            text = "$novikov::: В число команды входит немало новобранцев. Измученные и жалкие, они взирают на все вокруг с застывшим ужасом в глазах. Их пугает море, на которое они попали впервые, а ещё больше — неизвестное будущее. Даже среди кадровых моряков, окончивших различные специальные школы, не видно обычного веселья...",
            scene = scenes[2],
            options = listOf()
        ),

        23 to Dialogue(
            text = "$novikov::: Только штрафные, в противоположность остальным, держатся более или менее бодро. Береговое начальство, чтобы отделаться от них, как от вредного элемента, придумало самый лёгкий способ: списывать их на суда, отправляющиеся на войну. Таким образом, к ужасу нашему, у нас набралось их до семи процентов...",
            scene = scenes[2],
            options = listOf()
        ),

        24 to Dialogue(
            text = "Удручающая ситуация...",
            scene = scenes[2],
            options = listOf()
        ),

        25 to Dialogue(
            text = "Добравшись до Либавы мы с Новиковым направляемся в комментатуру флота доложить о прибытии. В коммендатуре все стоят на ушах - вот-вот флот должен выдвинуться в поход и суета наполнила портовый город",
            scene = scenes[2],
            options = listOf()
        ),

        26 to Dialogue(
            text = "Рожественский Зиновий Петрович - начальник штаба флота вскоре провёл обращение к командирам и старшим офицерам флота, на которое явился и я. Завтра этот мужчина из начальника штаба станет вице-адмиралом флота...",
            scene = scenes[2],
            options = listOf()
        ),

        27 to Dialogue(
            text = "$admiral::: На походе ночью не позволять никакому судну пересекать путь эскадры и приближаться к ней на расстояние менее 4 кабельтовых. Выстрелом под нос остановить приближающееся судно, указать ему курс выхода из запретной зоны или ожидать, пока эскадра не пройдёт. В случае невыполнения судном требований, применять по нему все виды оружия. Суда, не мешающие эскадре, освещать прожектором",
            scene = scenes[2],
            options = listOf()
        ),

        28 to Dialogue(
            text = "Кроме технических деталей похода, Рождественский пытался вдохновить офицеров перед операцией, которая вызывала беспокойство практически у всех, ведь мы идем на защиту крепости Порт-Артур, которая к моменту нашего пребытия вероятно уже падёт...",
            scene = scenes[2],
            options = listOf()
        ),

        29 to Dialogue(
            text = "И наш недавно собранный наспех флот встретится с японцами лицом к лицу на их территории. У врага будет преимущество в силе, снабжении... Об этом Рождественский конечно не упоминал - целью его обращения было вдохновить нас, а не наоборот",
            scene = scenes[2],
            options = listOf()
        ),

        30 to Dialogue(
            text = "После речи начальник штаба спешно отбыл, а я не имел шанса поприветствовать его лично. Сегодня я лишь переговорил с несколькими знакомыми офицерами и прибыл на свой корабль",
            scene = scenes[2],
            options = listOf()
        ),

        31 to Dialogue(
            text = "На причале стояли броненосцы: \"Князь Суворов\", \"Император Александр III\", \"Бородино\" и \"Орел\", транспорт \"Камчатка\", крейсеры: \"Аврора\", \"Светлана\", \"Алмаз\", миноносцы: \"Бедовый\", \"Блестящий\", \"Быстрый\"",
            scene = scenes[3],
            options = listOf()
        ),

        32 to Dialogue(
            text = "На рейде можно было разглядеть броненосцы: \"Ослябя\", \"Сисой Великий\" и \"Наварин\", крейсеры: \"Адмирал Нахимов\", \"Жемчуг\", транспорт \"Анадырь\", миноносцы: \"Буйный\", \"Бравый\", \"Бодрый\", \"Безупречный\"",
            scene = scenes[3],
            options = listOf()
        ),

        33 to Dialogue(
            text = "Прекрасный миноносец Грозный, недавно покрытый свежей краской дожидался своего командира и командир взошёл на борт",
            scene = scenes[3],
            options = listOf()
        ),

        34 to Dialogue(
            text = "$paramonov::: Капитан 2-го ранга Парамонов - Офицер артилерии",
            scene = scenes[3],
            options = listOf()
        ),

        35 to Dialogue(
            text = "$paramonov::: В отсутствии капитана 1-го ранка на корабле, ответственность за миноносец лежит на мне. Покажите ваши документы, полковник!",
            scene = scenes[3],
            options = listOf()
        ),

        36 to Dialogue(
            text = "Старший офицер на корабле проверил мои документы и судя по лицу он удовлетворён.",
            scene = scenes[3],
            options = listOf()
        ),

        37 to Dialogue(
            text = "$paramonov::: Приветствую Вас на корабле, капитан! Сведения о готовности корабля в этой рапортичке",
            scene = scenes[3],
            options = listOf()
        ),

        38 to Dialogue(
            text = "Офицер передал вам рапортичку, в которой перечислялось, сколько на корабле здоровых, больных, арестованных, какое количество тонн угля, на какое время хватит пресной воды и провианта",
            scene = scenes[3],
            options = listOf(
                Option(
                    text = "Принять рапортичку",
                    nextDialogueIndex = 39,
                    resourceEffect = Resource(0, 0, 5, 10, 10, 0, 0, 0, 0)
                ),
            )
        ),

        39 to Dialogue(
            text = "$paramonov::: Вы можете видеть сейчас на палубе весь офицерский состав корабля:",
            scene = scenes[3],
            options = listOf()
        ),

        40 to Dialogue(
            text = "$paramonov::: Капитан 2-го ранга Алексеев - офицер торпедного вооружения. Капитан лейтенант Ибрагимов - навигационный офицер. Капитан-поручик Магометов и капитан-поручик Феодосов - офицеры торпедного вооружения. Капитан-поручик Шмель - офицер связи. Капитан-пороучик Резцов - офицер разведки.",
            scene = scenes[3],
            options = listOf()
        ),

        41 to Dialogue(
            text = "$paramonov::: Подпоручик Гай - инженерные и ходовые системы. Карабельный врач Бухарин. К сожалению корабль недостаточно укомплектован механиками, инженерами и священником, а количество матросов на 10% ниже штатной численности. Но приказ адмирала о готовности корабля к походу поступил.",
            scene = scenes[3],
            options = listOf()
        ),

        42 to Dialogue(
            text = "$paramonov::: Какие будут распоряжения, капитан?",
            scene = scenes[3],
            options = listOf(
                Option(
                    text = "Проведем ревизию запасов и вооружения",
                    nextDialogueIndex = 43,
                    resourceEffect = Resource(0, 0, -1, -1, -1, 0, 0, 0, 0)
                ),
                Option(
                    text = "Всем отбой. Отдыхаем перед походом",
                    nextDialogueIndex = 44,
                    resourceEffect = Resource(0, 0, 1, 0, 0, 0, 0, 0, 0)
                ),
                Option(
                    text = "Выставить на ужин ендовы с водкой для команды, удвоить мерную кружку. Взять в кабаке бочку офицерам из лучших запасов.",
                    nextDialogueIndex = 44,
                    resourceEffect = Resource(-100, 1, 2, -1, 0, 0, 0, 0, 0)
                )
            )
        ),

        43 to Dialogue(
            text = "Ревизия затянулась до полуночи, а результат ревизии огорчил меня трижды. Во-первых ревизия обнаружила недостачу тяжелого автоматического оружия, во-вторых недостачу водки для матросов, а в третьих - команда недовольна как тем, что вместо отдыха перед походом пришлось заниматься работой, так и тем, что обнаружилась кража водки. Одно радует - что недостача обнаружилась сейчас, а не в середине похода и мы сможем восполнить ее при первой возможности",
            scene = scenes[3],
            options = listOf()
        ),

        44 to Dialogue(
            text = "2 октября 1904 года. Спал я тревожно и сон долго не шёл. Мне приснилось, как Грозный весь в пробоинах, со страшным креном и раненными матросами, лежащими на палубе на полном ходу уходит от попятам приследующего врага... Я встрехнул голову, прогоняя навождение и возвращаясь в реальность. Слышно, как гудят паровые машины, команды с разных кораблей флотилии, Вторая тихоокеанская эскадра просыпается и выдвигается в поход...",
            scene = scenes[3],
            options = listOf(
                Option(
                    text = "Следующая глава",
                    nextDialogueIndex = 50,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                )
            )

        ),

        /** ГЛАВА 2 ГУЛЛЬСКИЙ ИНЦИДЕНТ + ПОРТ БРЕСТ, ФРАНЦИЯ*/

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
            text = "РАДИОГРАММА (КАМЧАТКА)::: Мин не видили.",
            scene = scenes[5],
            options = listOf()
        ),

        60 to Dialogue(
            text = "РАДИОГРАММА (СУВОРОВ)::: Всем кораблям ожидать атаки миноносцев сзади.",
            scene = scenes[5],
            options = listOf()
        ),

        61 to Dialogue(
            text = "Капитан-поручики Шмель и Резцов таже в радиорубке прослушивали этот сеанс связи. При первых сообщениях с транспорта Камчатка на Шмель поднял тревогу на корабле, сейчас осталось ждать распоряжений адмирала и готовиться к возможному бою",
            scene = scenes[5],
            options = listOf()
        ),

        62 to Dialogue(
            text = "$bumblebee ::: Подозрительно мутная ситуация... В Датских водах японские корабли? Или чьи они..? ",
            scene = scenes[5],
            options = listOf()
        ),

        63 to Dialogue(
            text = "$reztsov ::: Путаница верно, со страху черти мерещатся. ",
            scene = scenes[5],
            options = listOf()
        ),

        64 to Dialogue(
            text = "Далее началась просто невообразимая суматоха: Телеграф сообщал, что СУВОРОВ включил боевые огни, его примеру последовали остальные корабли. Тут же часть кораблей начала сообщаять по телеграфу об обнаружении противника. Поступил приказ от адмирала открыть огонь. И я наконец услышал отдаленный рокот орудий главного калибра",
            scene = scenes[5],
            options = listOf()
        ),

        65 to Dialogue(
            text = "Огонь подхватили другие крейсера и броненосцы. Суматоха боя наполнила эскадру. Мой миноносец шёл далеко впереди от эскадры и начавшаяся суматоха нас не затронула - мы лишь слышали отдаленный гул основных орудий... Было слишком темно, чтобы пускать торпеды и самое главное, непонятно по кому. После приказа о движении в французский портовый город Брест других приказов миноносцам от адмирала не поступало... Я дал приказ развернуть корабль к флотлии, а наблюдатем рыскать прожекторами...",
            scene = scenes[5],
            options = listOf()
        ),

        66 to Dialogue(
            text = "РАДИОГРАММА (СУВОРОВ)::: Всем кораблям огонь прекратить.",
            scene = scenes[5],
            options = listOf()
        ),

        67 to Dialogue(
            text = "РАДИОГРАММА (СУВОРОВ)::: Всем кораблям о критических повреждениях должить.",
            scene = scenes[5],
            options = listOf()
        ),

        68 to Dialogue(
            text = "РАДИОГРАММА (СУВОРОВ)::: Всем кораблям следовать прежним курсом.",
            scene = scenes[5],
            options = listOf()
        ),

        69 to Dialogue(
            text = "Когда в Бресте собралась вся флотилия нам стало ясно, что никаких миноносцев не было... Идя по Доггерской Банке (так называют это богатое рыбой место) наша бравая флотилия врезались в рыбацкие корабли. Наблюдатели КАМЧАТКИ со страху перепутали утлые судёнышки с миноносцами",
            scene = scenes[5],
            options = listOf()
        ),

        70 to Dialogue(
            text = "Неопытные наблюдатели, страх и последующая паника привели к тому, что эскадра расстреливала рыбацкие шхуны... Более того, когда часть кораблей включила боевое освещение, другие приняли их за корабли противника и открыли по ним огонь. Те в ответ открыли встречный огонь...",
            scene = scenes[5],
            options = listOf()
        ),

        71 to Dialogue(
            text = "20 минут эскадра расстреливала рыбацкие шхуны и свои собственные корабли... Больше всего досталось крейсеру АВРОРА В него попало 5 снарядов, которыми был смертельно ранен судовой иеромонах Анастасий Рукин. На корабле пробиты дымовая труба, машинный кожух и надводный борт в трёх местах. На броненосце ОРЕЛ разорвало во время выстрела дульную часть 75-мм орудия.",
            scene = scenes[5],
            options = listOf()
        ),

        72 to Dialogue(
            text = "На следующий день портовый город шумел об этом инциденте. В прессе я прочел, что новость уже стала известна по всей Европе, постреляли мы рыбаков и друг друга на потеху миру...",
            scene = scenes[5],
            options = listOf()
        ),

        73 to Dialogue(
            text = "The Times::: It is almost inconceivable that any men calling themselves seamen, however frightened they might be, could spend twenty minutes bombarding a fleet of fishing boats without discovering the nature of their target.",
            scene = scenes[5],
            options = listOf(
                Option(
                    text = "Следующая глава",
                    nextDialogueIndex = 100,
                    resourceEffect = Resource(0, 1, -1, -1, 0, 0, 0, 0, 0)
                )
            )

        ),

        /** ГЛАВА 4 НЕОБЫЧНАЯ КНИГА: КАПИТАЛ*/

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
            options = if (capital > 1) { // Проверяем значение
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
//                        NotUsingThisCaseIfCapitalIsTrue = true // Устанавливаем значение, блокирующее этот вариант ответа для случая, если книга Капитал уже имеется
                    )
                )
            },
        ),

//        106 to Dialogue(
//            text = "$bookseller::: Вы ведь русские, да? Скромные 149 Российских Царских рублей господа! Уверяю вас, вы не пожалеете!",
//            scene = scenes[6],
//            listOf(
//                Option(
//                    text = "Купить книгу (ВНИМАНИЕ! Добавление книги в коллекцию может создать альтернативную историю развития событий)",
//                    nextDialogueIndex = 109,
//                    resourceEffect = Resource(-149, 0, 0, 0, 0, 1, 0, 0, 0),
//                    optionFunction = { setCapital(+1) }
//
//                ),
//                Option(
//                    text = "Отказаться от покупки",
//                    nextDialogueIndex = 110,
//                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
//                )
//            )
//        ),

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
            scene = scenes[4],
            options = listOf(
                Option(
                    text = "(чтение книг происходит автоматически в конце главы, оно не влияет на сюжет и может быть пропущено)",
                    nextDialogueIndex = 112,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),

        112 to Dialogue(
            text = getNextBookFragment(), // Получаем следующий фрагмент книги
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

        /** ГЛАВА 3 Виго, Испания*/

        113 to Dialogue(
            text = "Виго, Испания.::",
            scene = scenes[5],
            options = listOf()
        ),

        114 to Dialogue(
            text = "Виго, Испания",
            scene = scenes[5],
            options = listOf(
                Option(
                    text = "Следующая глава",
                    nextDialogueIndex = 151,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),

        /** ГЛАВА 3 Танжер, Марокко*/

        151 to Dialogue(
            text = "Танжер, Марокко.::",
            scene = scenes[5],
            options = listOf()
        ),

        152 to Dialogue(
            text = "Тут эскадра должна разделиться. Часть судов, осадка которых позволяет пройти через Суэцкий канал, пойдёт под командой адмирала Фелькерзама этим путем. Рожественский с главными силами поведёт корабли кругом Африки. Оба отряда соединятся на Мадагаскаре",
            scene = scenes[5],
            options = listOf()
        ),

        153 to Dialogue(
            text = "«Что де́лать?",
            scene = scenes[5],
            options = listOf(
                Option(
                    text = "Следующая глава",
                    nextDialogueIndex = 201,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),

        /** ГЛАВА 4 НЕОБЫЧНАЯ КНИГА ПРОДОЛЖЕНИЕ */

        201 to Dialogue(
            text = "Решив прогуляться по городу я зашёл на местный рынок и через некоторое время ко мне подошёл мужчина вполне обыкновенного для местности вида, но вёл себя он странно - оглядывался так, как-будто хотел сделать что-то противозаконное",
            scene = scenes[4],
            options = listOf()
        ),

        202 to Dialogue(
            text = "Капитан-пороучик Резцов, сопровождавший меня видимо сделал те же выводы, так как встал теперь между мной и этим сударем...",
            scene = scenes[4],
            options = listOf()
        ),

        203 to Dialogue(
            text = "$bookseller::: Не интересуют ли господ книги? У меня как раз есть один необычный экземпляр, вряд ли вы увидите такую у простых лавочников...",
            scene = scenes[7],
            options = listOf()
        ),

        204 to Dialogue(
            text = "$reztsov::: Ходебщик, так их у нас называют... Это букинисты, которые носят и предлагают к продаже книги",
            scene = scenes[4],
            options = listOf()
        ),

        205 to Dialogue(
            text = "Заинтригованный, я прошу показать что за книгу он хочет предложить",
            scene = scenes[4],
            options = listOf()
        ),

        206 to Dialogue(
            text = "Ходебщик разворачивает перекидной мешок, и достает толстую книгу, протягивая ее мне",
            scene = scenes[4],
            options = if (capital > 1) { // Проверяем значение
                listOf(
                    Option(
                        text = "Взглянув на обложку, разочарованно понимаю, что эту книгу читал, и она не представляет интереса",
                        nextDialogueIndex = 209,
                        resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0),
                    ),
                )
            } else {
                listOf(
                    Option(
                        text = "На незнакомой обложке красуется название: Что делать? Наболевшие вопросы нашего движения» — В. И. Ленин",
                        nextDialogueIndex = 207,
                        resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0),
//                        NotUsingThisCaseIfCapitalIsTrue = true // Устанавливаем значение, блокирующее этот вариант ответа для случая, если книга Капитал уже имеется
                    )
                )
            },
        ),

        207 to Dialogue(
            text = "Я открываю последниен страницы книги и в библиографических ссылках смотрю, нет ли знакомых источников. К своему удивлению нахожу книгу, которую начал недавно читать.",
            scene = scenes[4],
            listOf(
                Option(
                    text = "Купить книгу (-49 Царских Рублей)",
                    nextDialogueIndex = 208,
                    resourceEffect = Resource(-49, 0, 0, 0, 0, 1, 0, 0, 0)
                ),
                Option(
                    text = "Отказаться от покупки",
                    nextDialogueIndex = 209,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0),
                )
            )
        ),

        208 to Dialogue(
            text = "Расчитавшись с торговцем беру книгу из его дрожащих рук...",
            scene = scenes[4],
            options = listOf()
        ),

        209 to Dialogue(
            text = "На рынке больше нечего смотреть и нам пора вернуться на корабль",
            scene = scenes[4],
            options = listOf(
                Option(
                    text = "Следующая глава",
                    nextDialogueIndex = 301,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),

        /** ГЛАВА 6 Суэцкий канал */

        301 to Dialogue(
            text = "Суэцкий канал, Египет::",
            scene = scenes[4],
            options = listOf()
        ),

        302 to Dialogue(
            text = "_",
            scene = scenes[5],
            options = listOf(
                Option(
                    text = "Следующая глава",
                    nextDialogueIndex = 351,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),

        /** ГЛАВА 7 Мадагаскар */

        351 to Dialogue(
            text = "Мадагаскар.::",
            scene = scenes[4],
            options = listOf()
        ),

        352 to Dialogue(
            text = "25 декабря 1904 года.",
            scene = scenes[5],
            options = listOf(
                Option(
                    text = "Следующая глава",
                    nextDialogueIndex = 401,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),

        /** ГЛАВА 8 присоединился отряд Добротворского. Малакский пролив*/

        401 to Dialogue(
            text = "Малакский пролив::",
            scene = scenes[4],
            options = listOf()
        ),

        402 to Dialogue(
            text = "1 февраля 1905 года. Присоединился отряд Добротворского. 3 марта эскадра вышла в море, проложив курс к Малакскому проливу",
            scene = scenes[5],
            options = listOf(
                Option(
                    text = "Следующая глава",
                    nextDialogueIndex = 451,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),

        /** ГЛАВА 9 бухта Камран */

        451 to Dialogue(
            text = "бухта Камран, Сайгон.::",
            scene = scenes[4],
            options = listOf()
        ),

        452 to Dialogue(
            text = "1 апреля 1905 года. Эскадра прибыла в бухту Камран (на Индо-Китайском п-ве, вблизи Сайгона)",
            scene = scenes[5],
            options = listOf(
                Option(
                    text = "Следующая глава",
                    nextDialogueIndex = 501,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),

        /** ГЛАВА 10 бухта Ван-Фонг, */

        501 to Dialogue(
            text = "бухта Ван-Фонг, Сайгон.::",
            scene = scenes[4],
            options = listOf()
        ),

        502 to Dialogue(
            text = "24 апреля 1905 года. Прибыл отряд Небогатова.",
            scene = scenes[5],
            options = listOf(
                Option(
                    text = "Следующая глава",
                    nextDialogueIndex = 560,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),

        /** ГЛАВА 11 Цусимское сражение */

        560 to Dialogue(
            text = "Цусимское сражение.::",
            scene = scenes[4],
            options = listOf()
        ),
    )
}