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
