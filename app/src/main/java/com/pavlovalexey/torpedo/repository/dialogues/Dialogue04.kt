package com.pavlovalexey.torpedo.repository.dialogues

import com.pavlovalexey.torpedo.model.Characters.admiral
import com.pavlovalexey.torpedo.model.Characters.bumblebee
import com.pavlovalexey.torpedo.model.Characters.end
import com.pavlovalexey.torpedo.model.Characters.novikov
import com.pavlovalexey.torpedo.model.Characters.paramonov
import com.pavlovalexey.torpedo.model.Characters.reztsov
import com.pavlovalexey.torpedo.model.Dialogue
import com.pavlovalexey.torpedo.model.Option
import com.pavlovalexey.torpedo.model.Resource

/** ГЛАВА 4 Виго, Испания*/

object Dialogue04 {

    internal val dialogues: List<Pair<Int, Dialogue>> = listOf(
        113 to Dialogue(
            text = "ВИГО, ИСПАНИЯ.::",
            scene = scenes[16],
            options = listOf()
        ),

        114 to Dialogue(
            text = "Когда я стоял на палубе и любовался океаном, я заметил странное существо, которое " +
                    "ползло по борту корабля, пока не остановилось рядом со мной. Оно напоминало " +
                    "рыбу, по форме похожую на скумбрию, но какую-то больную, с щупальцами, торчащими из брюшка. Рыба прыгнула на меня " +
                    "и прилипнув к телу начала подниматься вверх, как будто ведомая неизвестной силой " +
                    "из глубин океана. Мои ноги и горло стали ватными, и я не мог ни шевелиться, ни " +
                    "кричать. У твари с раненной брюшины сочилась жидкость, пачкая мою форму. Внезапно " +
                    "рыба открыла глаза и начала произносить слова. Сначала это было как-бы шепотом, " +
                    "но затем яснее, хотя смысл произносимых слов оставался совершенно непонятен",
            scene = scenes[16],
            options = listOf()
        ),

        115 to Dialogue(
            text = "Пнглуи мглунафх Ктулху Р’лайх угахнагл фтагн",
            scene = scenes[16],
            options = listOf()
        ),

        116 to Dialogue(
            text = "Пнглуи мглунафх Ктулху Р’лайх угахнагл фтагн...",
            scene = scenes[16],
            options = listOf()
        ),

        117 to Dialogue(
            text = "Пасть чудовища раскрывалась в истошном немом крике, а слова доносились из её чрева. Моя рука " +
                    "с револьвером медленно поднималась, пальчик готов спустить курок, " +
                    "и прекратить этот кошмар, но рука продолжила круговое движение, не останавливаясь на рыбине," +
                    "направляя револьвер в моё лицо, как я не пытался управлять ей, попытки были тщетны. " +
                    "Я чувствовал запах пороха, а палец все сильнее сжимал курок. По щекам моим бежали " +
                    "слезы... Скумбрия наблюдала, не сводя стеклянных глаз. " +
                    "Пнглуи мглунафх Ктулху Р’лайх угахнагл фтагн",
            scene = scenes[16],
            options = listOf()
        ),

        118 to Dialogue(
            text = "Я вскочил с постели покрытый капельками пота, какой ужастный кошмар!",
            scene = scenes[16],
            options = listOf()
        ),
        119 to Dialogue(
            text = "Наши корабли приближались к гавани Виго. Мы встретили прекрасную бухту, утопающую " +
                    "в зелени и окруженную высокими " +
                    "горами. Побережье украшали небольшие рыбацкие деревни. Салютом мы " +
                    "почтили испанский флаг и бросили " +
                    "якорь. В ответ на наш приход раздался громкий салют из орудий крепости. День был " +
                    "ясным и безветренным, солнечные лучи ласково обливали каменные стены и здания, " +
                    "вздымающиеся на склонах горы. Над городом возвышалась крепость, а вдали " +
                    "простиралась горная цепь Пиренеи...",
            scene = scenes[17],
            options = listOf()
        ),

        120 to Dialogue(
            text = "Виго, Испания",
            options = listOf(
                Option(
                    text = "Игра в разработке, продолжение следует...",
                    nextDialogueIndex = 0,
                    resourceEffect = Resource(0, 0, 0, 0, 0, 0, 0, 0, 0)
                )
            )
        ),
    )
}






