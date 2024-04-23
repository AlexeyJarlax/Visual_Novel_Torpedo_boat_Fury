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
            text = "Виго, Испания.::",
            scene = scenes[14],
            options = listOf()
        ),

        114 to Dialogue(
            text = "Виго, Испания",
            options = listOf(
                Option(
                    text = "Игра в разработке, продолжение следует...",
                    nextDialogueIndex = 0,
                    resourceEffect = Resource(0*0, 0*0, 0*0, 0*0, 0*0, 0*0, 0*0, 0*0, 0*0)
                )
            )
        ),
    )
}