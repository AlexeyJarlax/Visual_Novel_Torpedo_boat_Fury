package com.pavlovalexey.torpedo.repository

/** основной блок кода сюжета Visual Novel "Torpedo Boat Grozny, содержащий в себе сцены, диалоги и прочие детали сюжета. Сюжет пишется только тут.*/

import android.content.Context
import android.media.MediaPlayer
import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Option
import com.pavlovalexey.torpedo.model.Resource
import com.pavlovalexey.torpedo.model.Scene
import com.pavlovalexey.torpedo.model.Scenes
import com.pavlovalexey.torpedo.model.Music
import com.pavlovalexey.torpedo.model.MusicList
import com.pavlovalexey.torpedo.repository.dialogues.DialogueManager.getDialogues

class GameRepositoryImpl(
    private val context: Context,
    private val initialResource: Resource
) : GameRepository {

//    private val scenes: List<Scene> = Scenes.list
    private var currentResource: Resource = initialResource
    private val bookText = context.getString(R.string.kapital)
    private var lastReadFragment: String = ""
    private var currentBookPosition: Int = 0
    private var lastUsedScene: Scene? = null
    private val musicList: List<Music> = MusicList.list
    private var mediaPlayer: MediaPlayer? = null
    private var currentMusicIndex: Int = 0

    init {
        playMusic()
    }

    override fun getInitialDialogue(): Dialogue {
        val dialogues = getDialogues(Scenes.list)
        val initialDialogue =
            dialogues.firstOrNull()?.second ?: throw IllegalStateException("Диалоги недоступны")
        lastUsedScene = initialDialogue.scene
        return initialDialogue
    }

    override fun getInitialScene(): Scene {
        return Scenes.list.firstOrNull()?.apply {
            lastUsedScene = this
        } ?: throw IllegalStateException("Сцены недоступны")
    }

    private val dialogues: List<Pair<Int, Dialogue>> = getDialogues(Scenes.list)

    override fun getResource(): Resource {
        return currentResource
    }

    override fun updateResources(resourceEffect: Resource) {
        currentResource = Resource(
            currentResource.rubles + resourceEffect.rubles,
            currentResource.fame + resourceEffect.fame,
            currentResource.teamLoyalty + resourceEffect.teamLoyalty,
            currentResource.vodka + resourceEffect.vodka,
            currentResource.maxim + resourceEffect.maxim,
            currentResource.capital + resourceEffect.capital,
            currentResource.necronomicon + resourceEffect.necronomicon,
            currentResource.neisvestno + resourceEffect.neisvestno,
            currentResource.relationship + resourceEffect.relationship
        )
    }

    override fun updateDialogueWithNextFragment(dialogue: Dialogue, nextFragment: String) {
        dialogue.apply {
            scene = lastUsedScene
        }
        dialogue.text = lastReadFragment
    }

    override fun getDialogueByIndex(index: Int): Dialogue? {
        val dialogue = dialogues.find { it.first == index }?.second
        dialogue?.let {
            if (index == 112 || index == 211) {
                val nextFragment = getNextBookFragment()
                updateDialogueWithNextFragment(it, nextFragment)
            }
            val resourceEffect = it.options.firstOrNull()?.resourceEffect
            resourceEffect?.let { effect ->
                updateResources(effect)
            }
            lastUsedScene = it.scene ?: lastUsedScene

        }
        return dialogue
    }

    override fun getNextBookFragment(): String {
        currentBookPosition += 200
        val endIndex = currentBookPosition + 600
        lastReadFragment = if (currentBookPosition < bookText.length) {
            if (endIndex < bookText.length) {
                bookText.substring(currentBookPosition, endIndex)
            } else {
                bookText.substring(currentBookPosition)
            }
        } else {
            "End of the book"
        }
        lastReadFragment += "---"
        return lastReadFragment
    }

    private fun playMusic() {
        val music = musicList[currentMusicIndex]
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, music.track)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()

        mediaPlayer?.setOnCompletionListener {
            playNextMusic()
        }
    }

    private fun playNextMusic() {
        currentMusicIndex = (currentMusicIndex + 1) % musicList.size
        playMusic()
    }
}


//
//        /** ГЛАВА 2 ГУЛЛЬСКИЙ ИНЦИДЕНТ + ПОРТ БРЕСТ, ФРАНЦИЯ*/
//
//        50 to Dialogue(
//            text = "** ГУЛЛЬСКИЙ ИНЦИДЕНТ **",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        51 to Dialogue(
//            text = "РАДИОГРАММА (КАМЧАТКА)::: Преследуют миноносцы.",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        52 to Dialogue(
//            text = "РАДИОГРАММА (СУВОРОВ)::: За вами погоня? Сколько миноносцев и от какого румба?",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        53 to Dialogue(
//            text = "РАДИОГРАММА (КАМЧАТКА)::: Атака со всех сторон.",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        54 to Dialogue(
//            text = "РАДИОГРАММА (СУВОРОВ)::: Сколько миноносцев? Сообщите!",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        55 to Dialogue(
//            text = "РАДИОГРАММА (КАМЧАТКА)::: Миноносцев около восьми.",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        56 to Dialogue(
//            text = "РАДИОГРАММА (СУВОРОВ)::: Близко к вам?",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        57 to Dialogue(
//            text = "РАДИОГРАММА (КАМЧАТКА)::: Более кабельтова.",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        58 to Dialogue(
//            text = "РАДИОГРАММА (СУВОРОВ)::: Пускали ли мины?",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        59 to Dialogue(
//            text = "РАДИОГРАММА (КАМЧАТКА)::: Мин не видили.",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        60 to Dialogue(
//            text = "РАДИОГРАММА (СУВОРОВ)::: Всем кораблям ожидать атаки миноносцев сзади.",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        61 to Dialogue(
//            text = "Капитан-поручики Шмель и Резцов таже в радиорубке прослушивали этот сеанс связи. При первых сообщениях с транспорта Камчатка на Шмель поднял тревогу на корабле, сейчас " +
//                    "осталось ждать распоряжений адмирала и готовиться к возможному бою",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        62 to Dialogue(
//            text = "$bumblebee ::: Подозрительно мутная ситуация... В Датских водах японские корабли? Или чьи они..? ",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        63 to Dialogue(
//            text = "$reztsov ::: Путаница верно, со страху черти мерещатся. ",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        64 to Dialogue(
//            text = "Далее началась просто невообразимая суматоха: Телеграф сообщал, что СУВОРОВ включил боевые огни, его примеру последовали остальные корабли. Тут же часть кораблей начала " +
//                    "сообщаять по телеграфу об обнаружении противника. Поступил приказ от адмирала открыть огонь. И я наконец услышал отдаленный рокот орудий главного калибра",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        65 to Dialogue(
//            text = "Огонь подхватили другие крейсера и броненосцы. Суматоха боя наполнила эскадру. Мой миноносец шёл далеко впереди от эскадры и начавшаяся суматоха нас не затронула - мы " +
//                    "лишь слышали отдаленный гул основных орудий... Было слишком темно, чтобы пускать торпеды и самое главное, непонятно по кому. После приказа о движении в французский " +
//                    "портовый город Брест других приказов миноносцам от адмирала не поступало... Я дал приказ развернуть корабль к флотлии, а наблюдатем рыскать прожекторами...",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        66 to Dialogue(
//            text = "РАДИОГРАММА (СУВОРОВ)::: Всем кораблям огонь прекратить.",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        67 to Dialogue(
//            text = "РАДИОГРАММА (СУВОРОВ)::: Всем кораблям о критических повреждениях должить.",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        68 to Dialogue(
//            text = "РАДИОГРАММА (СУВОРОВ)::: Всем кораблям следовать прежним курсом.",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        69 to Dialogue(
//            text = "Когда в Бресте собралась вся флотилия нам стало ясно, что никаких миноносцев не было... Идя по Доггерской Банке (так называют это богатое рыбой место) наша бравая флотилия " +
//                    "врезались в рыбацкие корабли. Наблюдатели КАМЧАТКИ со страху перепутали утлые судёнышки с миноносцами",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        70 to Dialogue(
//            text = "Неопытные наблюдатели, страх и последующая паника привели к тому, что эскадра расстреливала рыбацкие шхуны... Более того, когда часть кораблей включила боевое освещение, " +
//                    "другие приняли их за корабли противника и открыли по ним огонь. Те в ответ открыли встречный огонь...",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        71 to Dialogue(
//            text = "20 минут эскадра расстреливала рыбацкие шхуны и свои собственные корабли... Больше всего досталось крейсеру АВРОРА В него попало 5 снарядов, которыми был смертельно " +
//                    "ранен судовой иеромонах Анастасий Рукин. На корабле пробиты дымовая труба, машинный кожух и надводный борт в трёх местах. На броненосце ОРЕЛ разорвало во время выстрела " +
//                    "дульную часть 75-мм орудия.",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        72 to Dialogue(
//            text = "На следующий день портовый город шумел об этом инциденте. В прессе я прочел, что новость уже стала известна по всей Европе, постреляли мы рыбаков и друг друга на " +
//                    "потеху миру...",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        73 to Dialogue(
//            text = "The Times::: It is almost inconceivable that any men calling themselves seamen, however frightened they might be, could spend twenty minutes bombarding a fleet of " +
//                    "fishing boats without discovering the nature of their target.",
//            scene = scenes[5],
//            options = listOf(
//                Option(
//                    text = "Следующая глава",
//                    nextDialogueIndex = 100,
//                    resourceEffect = Resource(0, 1, -1, -1, 0, 0, 0, 0, 0)
//                )
//            )
//
//        ),
//
//        /** ГЛАВА 4 НЕОБЫЧНАЯ КНИГА: КАПИТАЛ*/
//
//        100 to Dialogue(
//            text = "Решив прогуляться по городу я зашёл на местный рынок и через некоторое время, сам того не заметив уже изучал книги в большой книжной лавке",
//            scene = scenes[4],
//            options = listOf()
//        ),
//
//        101 to Dialogue(
//            text = "Капитан-поручик Резцов, сопровождавший меня на берегу в целях безопастности, держался рядом, со скучающим видом листая газету",
//            scene = scenes[9],
//            options = listOf()
//        ),
//
//        102 to Dialogue(
//            text = "$bookseller::: Не интересуют ли господ книги? У меня как раз есть один ценный и необычный экземпляр, вряд ли вы увидите такую у простых лавочников...",
//            scene = scenes[6],
//            options = listOf()
//        ),
//
//        103 to Dialogue(
//            text = "$reztsov::: Мы офицеры и люди образованные, вряд ли вы сможете нас чем-то удивить, сударь",
//            scene = scenes[9],
//            options = listOf()
//        ),
//
//        104 to Dialogue(
//            text = "Резцов произнёс это на языке торговца практически без акцента, что меня изрядно удивило",
//            scene = scenes[9],
//            options = listOf()
//        ),
//
//        105 to Dialogue(
//            text = "Торговец открывает одну из полок его большого письменного стола ключом и достает толстую книгу, протягивая ее мне, так как я стоял ближе",
//            scene = scenes[6],
//            options = if (currentResource.capital > 1) { // Проверяем значение
//                listOf(
//                    Option(
//                        text = "Взглянув на обложку, разочарованно понимаю, что эту книгу я читал, и интереса она не представляет",
//                        nextDialogueIndex = 110,
//                        resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
//                    ),
//                )
//            } else {
//                listOf(
//                    Option(
//                        text = "На незнакомой обложке красуется название: Капитал. Автор книги: Карл Маркс...",
//                        nextDialogueIndex = 106,
//                        resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
//                    )
//                )
//            },
//        ),
//
//        106 to Dialogue(
//            text = "$bookseller::: Вы ведь русские, да? Скромные 149 Российских Царских рублей господа! Уверяю вас, вы не пожалеете!",
//            scene = scenes[6],
//            options = listOf(
//                Option(
//                    text = "Купить книгу (ВНИМАНИЕ! Добавление книги в коллекцию может создать альтернативную историю развития событий)",
//                    nextDialogueIndex = 109,
//                    resourceEffect = Resource(-149, 0, 0, 0, 0, 1, 0, 0, 0),
//                    optionFunction = { currentResource.capital += 1 }
//                ),
//                Option(
//                    text = "Отказаться от покупки",
//                    nextDialogueIndex = 110,
//                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
//                )
//            )
//        ),
//
//        109 to Dialogue(
//            text = "Расчитавшись с торговцем беру книгу из его морщинистых рук...",
//            scene = scenes[6],
//            options = listOf()
//        ),
//
//        110 to Dialogue(
//            text = "Тут больше нечего смотреть и нам пора вернуться на корабль",
//            scene = scenes[4],
//            options = listOf()
//        ),
//
//        111 to Dialogue(
//            text = "Конец дня, я могу уединиться в своей каюте и полистать одну из книг коллекции",
//            scene = scenes[8],
//            options = listOf(
//                Option(
//                    text = "(чтение книг происходит автоматически в конце главы, оно не влияет на сюжет и может быть пропущено)",
//                    nextDialogueIndex = 112,
//                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
//                )
//            )
//        ),
//
//        112 to Dialogue(
////            text = getNextBookFragment(), // Получаем следующий фрагмент книги
//            scene = scenes[8],
//            options = listOf(
//                Option(
//                    text = "следующая страница",
//                    nextDialogueIndex = 112, // Оставляем тот же индекс диалога
//                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
//                ),
//                Option(
//                    text = "закрыть книгу",
//                    nextDialogueIndex = 113,
//                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
//                )
//            )
//        ),
//
//        /** ГЛАВА 3 Виго, Испания*/
//
//        113 to Dialogue(
//            text = "Виго, Испания.::",
//            scene = scenes[14],
//            options = listOf()
//        ),
//
//        114 to Dialogue(
//            text = "Виго, Испания",
//            options = listOf(
//                Option(
//                    text = "...",
//                    nextDialogueIndex = 561,
//                )
//            )
//        ),
//
//        /** ГЛАВА 3 Танжер, Марокко*/
//
//        151 to Dialogue(
//            text = "Танжер, Марокко.::",
//            options = listOf()
//        ),
//
//        152 to Dialogue(
//            text = "Тут эскадра должна разделиться. Часть судов, осадка которых позволяет пройти через Суэцкий канал, пойдёт под командой адмирала Фелькерзама этим путем. Рожественский " +
//                    "с главными силами поведёт корабли кругом Африки. Оба отряда соединятся на Мадагаскаре",
//            scene = scenes[5],
//            options = listOf()
//        ),
//
//        153 to Dialogue(
//            text = "«Что де́лать?",
//            options = listOf(
//                Option(
//                    text = "Следующая глава",
//                    nextDialogueIndex = 201,
//                )
//            )
//        ),
//
//        /** ГЛАВА 4 НЕОБЫЧНАЯ КНИГА ПРОДОЛЖЕНИЕ */
//
//        201 to Dialogue(
//            text = "Решив прогуляться по городу я зашёл на местный рынок и через некоторое время ко мне подошёл мужчина вполне обыкновенного для местности вида, но вёл себя он странно - " +
//                    "оглядывался так, как-будто хотел сделать что-то противозаконное",
//            scene = scenes[15],
//            options = listOf()
//        ),
//
//        202 to Dialogue(
//            text = "Капитан-пороучик Резцов, сопровождавший меня видимо сделал те же выводы, так как встал теперь между мной и этим сударем...",
//            scene = scenes[9],
//            options = listOf()
//        ),
//
//        203 to Dialogue(
//            text = "$bookseller::: Не интересуют ли господ книги? У меня как раз есть один необычный экземпляр, вряд ли вы увидите такую у простых лавочников...",
//            scene = scenes[7],
//            options = listOf()
//        ),
//
//        204 to Dialogue(
//            text = "$reztsov::: Ходебщик, так их у нас называют... Это букинисты, которые носят и предлагают к продаже книги",
//            scene = scenes[9],
//            options = listOf()
//        ),
//
//        205 to Dialogue(
//            text = "Заинтригованный, я прошу показать что за книгу он хочет предложить",
//            scene = scenes[7],
//            options = listOf()
//        ),
//
//        206 to Dialogue(
//            text = "Ходебщик разворачивает перекидной мешок, и достает толстую книгу, протягивая ее мне",
//            options = if (currentResource.capital > 1) { // Проверяем значение
//                listOf(
//                    Option(
//                        text = "Взглянув на обложку, разочарованно понимаю, что эту книгу читал, и она не представляет интереса",
//                        nextDialogueIndex = 209,
//                    ),
//                )
//            } else {
//                listOf(
//                    Option(
//                        text = "На незнакомой обложке красуется название: Что делать? Наболевшие вопросы нашего движения» — В. И. Ленин",
//                        nextDialogueIndex = 207,
////                        NotUsingThisCaseIfCapitalIsTrue = true // Устанавливаем значение, блокирующее этот вариант ответа для случая, если книга Капитал уже имеется
//                    )
//                )
//            },
//        ),
//
//        207 to Dialogue(
//            text = "Я открываю последниен страницы книги и в библиографических ссылках смотрю, нет ли знакомых источников. К своему удивлению нахожу книгу, которую начал недавно читать.",
//            options = listOf(
//                Option(
//                    text = "Купить книгу (-49 Царских Рублей)",
//                    nextDialogueIndex = 208,
//                    resourceEffect = Resource(-49, 0, 0, 0, 0, 1, 0, 0, 0)
//                ),
//                Option(
//                    text = "Отказаться от покупки",
//                    nextDialogueIndex = 209,
//                )
//            )
//        ),
//
//        208 to Dialogue(
//            text = "Расчитавшись с торговцем беру книгу из его дрожащих рук...",
//            options = listOf()
//        ),
//
//        209 to Dialogue(
//            text = "На рынке больше нечего смотреть и нам пора вернуться на корабль",
//            scene = scenes[15],
//            options = listOf(
//                Option(
//                    text = "Следующая глава",
//                    nextDialogueIndex = 210,
//                )
//            )
//        ),
//        210 to Dialogue(
//            text = "Конец дня, я могу уединиться в своей каюте и полистать одну из книг коллекции",
//            scene = scenes[8],
//            options = listOf(
//                Option(
//                    text = "(чтение книг происходит автоматически в конце главы, оно не влияет на сюжет и может быть пропущено)",
//                    nextDialogueIndex = 211
//                )
//            )
//        ),
//        211 to Dialogue(
////            text = getNextBookFragment(), // Получаем следующий фрагмент книги
//            options = listOf(
//                Option(
//                    text = "следующая страница",
//                    nextDialogueIndex = 211,
//                ),
//                Option(
//                    text = "закрыть книгу",
//                    nextDialogueIndex = 301
//                )
//            )
//        ),
//
//        /** ГЛАВА 6 Суэцкий канал */
//
//        301 to Dialogue(
//            text = "Суэцкий канал, Египет::",
//            scene = scenes[15],
//            options = listOf()
//        ),
//
//        302 to Dialogue(
//            options = listOf(
//                Option(
//                    text = "Следующая глава",
//                    nextDialogueIndex = 351,
//                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
//                )
//            )
//        ),
//
//        /** ГЛАВА 7 Мадагаскар */
//
//        351 to Dialogue(
//            text = "Мадагаскар.::",
//            scene = scenes[14],
//            options = listOf()
//        ),
//
//        352 to Dialogue(
//            text = "25 декабря 1904 года.",
//            options = listOf(
//                Option(
//                    text = "Следующая глава",
//                    nextDialogueIndex = 401,
//                )
//            )
//        ),
//
//        /** ГЛАВА 8 присоединился отряд Добротворского. Малакский пролив*/
//
//        401 to Dialogue(
//            text = "Малакский пролив::",
//            options = listOf()
//        ),
//
//        402 to Dialogue(
//            text = "1 февраля 1905 года. Присоединился отряд Добротворского. 3 марта эскадра вышла в море, проложив курс к Малакскому проливу",
//            options = listOf(
//                Option(
//                    text = "Следующая глава",
//                    nextDialogueIndex = 451,
//                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
//                )
//            )
//        ),
//
//        /** ГЛАВА 9 бухта Камран */
//
//        451 to Dialogue(
//            text = "бухта Камран, Сайгон.::",
//            options = listOf()
//        ),
//
//        452 to Dialogue(
//            text = "1 апреля 1905 года. Эскадра прибыла в бухту Камран (на Индо-Китайском п-ве, вблизи Сайгона)",
//            options = listOf(
//                Option(
//                    text = "Следующая глава",
//                    nextDialogueIndex = 501,
//                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
//                )
//            )
//        ),
//
//        /** ГЛАВА 10 бухта Ван-Фонг, */
//
//        501 to Dialogue(
//            text = "бухта Ван-Фонг, Сайгон.::",
//            options = listOf()
//        ),
//
//        502 to Dialogue(
//            text = "24 апреля 1905 года. Прибыл отряд Небогатова.",
//            options = listOf(
//                Option(
//                    text = "Следующая глава",
//                    nextDialogueIndex = 560,
//                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
//                )
//            )
//        ),
//
//        /** ГЛАВА 11 Цусимское сражение */
//
//        560 to Dialogue(
//            text = "Цусимское сражение.::",
//            options = listOf()
//        ),
//        561 to Dialogue(
//            text = "Продолжение следует...",
//            options = listOf(
//            )
//        ),
//    )
