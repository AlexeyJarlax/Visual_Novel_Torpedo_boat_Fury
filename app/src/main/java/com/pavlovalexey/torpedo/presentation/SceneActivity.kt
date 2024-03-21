package com.pavlovalexey.torpedo.presentation

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.domain.DialogNavigator

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
        findViewById<View>(android.R.id.content).setOnClickListener {
            showNextDialog()
        }
    }

    private fun showNextScene() {
        if (currentActIndex < Story.plotActs.size) {
            val plotAct = Story.plotActs[currentActIndex]
            val scenes = plotAct.scenes
            val sceneCounts = scenes.size

            if (currentSceneIndex < sceneCounts) {
                val scene = scenes[currentSceneIndex]
                sceneView.showScene(scene)
                dialogViewModel = DialogViewModel(scene.dialogs)
                showNextDialog()
                dialogView.showDialog(scene.dialogs[currentSceneIndex])
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
        dialogViewModel.getNextDialog()?.let { dialog ->
            if (dialog.choices?.isNotEmpty() == true) {
                dialog.choices?.let { dialogView.showOptions(it) }
            } else {
                dialogView.showDialog(dialog)
            }
        } ?: run {
            currentSceneIndex++
            showNextScene()
        }
    }

    fun onDialogOptionSelected(option: String) {
        dialogView.clearOptions()
        Story.processChoice(option, dialogViewModel)?.let { nextDialog ->
            dialogView.showDialog(nextDialog)
        } ?: run {
            showNextDialog()
        }
    }

    fun onSkipButtonClicked() {
        // Handle skip button click event
        showNextDialog()
    }
}
