package com.pavlovalexey.torpedo.presentation

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.data.Dialog
import com.pavlovalexey.torpedo.domain.DialogNavigator
import com.pavlovalexey.torpedo.presentation.Story.story

class SceneActivity : AppCompatActivity(), DialogNavigator {
    private lateinit var mediaPlayer: MediaPlayer
    private var currentActIndex = 0
    private var currentSceneIndex = 0
    private lateinit var dialogViewModel: DialogViewModel
    private lateinit var sceneView: SceneView
    private lateinit var dialogView: DialogView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene)
        sceneView = findViewById(R.id.sceneView)
        dialogView = findViewById(R.id.dialogView)
        mediaPlayer = MediaPlayer.create(this, R.raw.odettes_theme)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
        showNextScene()
    }

    private fun showNextScene() {
        if (currentActIndex < Story.plotActs.size) {
            val plotAct = Story.plotActs[currentActIndex]
            val scenes = plotAct.scenes
            if (currentSceneIndex < scenes.size) {
                val scene = scenes[currentSceneIndex]
                sceneView.showScene(scene)
                dialogViewModel = DialogViewModel(scene.dialogs, this)
                dialogViewModel.getNextDialog()?.let { dialogView.showDialog(it) }
            } else {
                currentActIndex++
                currentSceneIndex = 0
                showNextScene()
            }
        } else {
            // Handle actions after finishing the story
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }

    override fun showNextDialog() {
        // Implement the logic to show the next dialog
        // You can call the necessary functions here
    }

    // Implement the function to handle the user's dialog option selection
    fun onDialogOptionSelected(option: String) {
        // Example of handling different dialog options
        when (option) {
            "Провести ревизию вооружения" -> {
                // Handle the case when the user chooses to inspect the weapons
                // You can show the next dialog accordingly
                dialogView.showDialog(Dialog(story, "Ревизия вооружения начата..."))
            }
            "Осмотреть команду" -> {
                // Handle the case when the user chooses to inspect the team
                // You can show the next dialog accordingly
                dialogView.showDialog(Dialog(story, "Осмотр команды начат..."))
            }
            else -> {
                // Handle other options if needed
            }
        }
    }
}
