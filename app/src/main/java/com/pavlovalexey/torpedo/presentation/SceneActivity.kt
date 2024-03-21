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
    private var dialogOptionsVisible = false

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

    private fun initDialogOptions() {
        findViewById<Button>(R.id.button1).setOnClickListener {
            onDialogOptionSelected("Провести ревизию вооружения")
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            onDialogOptionSelected("Осмотреть команду")
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
                dialogViewModel.getNextDialog()?.let { dialogView.showDialog(it) }
                currentSceneIndex++
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
        dialogViewModel.getNextDialog()?.let { dialogView.showDialog(it) }
    }

    fun onDialogOptionSelected(option: String) {
        if (dialogOptionsVisible) {
            findViewById<View>(R.id.button1).visibility = View.INVISIBLE
            findViewById<View>(R.id.button2).visibility = View.INVISIBLE
            Story.processChoice(option, dialogViewModel)?.let { nextDialog ->
                dialogView.showDialog(nextDialog)
            } ?: showNextScene()
        }
    }
}
