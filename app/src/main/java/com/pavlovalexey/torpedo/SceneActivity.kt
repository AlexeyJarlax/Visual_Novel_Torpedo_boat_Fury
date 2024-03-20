package com.pavlovalexey.torpedo

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SceneActivity : AppCompatActivity() {
    private lateinit var sceneView: SceneView
    private lateinit var dialogView: DialogView
    private lateinit var dialogViewModel: DialogViewModel
    private lateinit var mediaPlayer: MediaPlayer
    private var currentActIndex = 0
    private var currentSceneIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene)
        sceneView = findViewById(R.id.sceneView)
        dialogView = findViewById(R.id.dialogView)
        mediaPlayer = MediaPlayer.create(this, R.raw.odettes_theme)
        mediaPlayer.isLooping = true // Повторять воспроизведение
        mediaPlayer.start()
        val firstSceneDialogs = Story.plotActs.first().scenes.first().dialogs
        dialogViewModel = DialogViewModel(firstSceneDialogs)

        // Отображение первой сцены при запуске
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
                dialogViewModel = DialogViewModel(scene.dialogs) // Обновляем ViewModel с новыми диалогами
                dialogViewModel.getNextDialog()?.let { dialogView.showNextDialog(it) } // Показываем первый диалог из новой сцены
            } else {
                // Если достигнут конец сцен в текущем акте, переходим к следующему акту
                currentActIndex++
                currentSceneIndex = 0
                showNextScene()
            }
        } else {
            // Действия после завершения сюжета
        }
    }
    private fun showNextDialog() {
        val dialog = dialogViewModel.getNextDialog()
        dialog?.let {
            dialogView.showNextDialog(it)
            // будет логика для ожидания взаимодействия пользователя
        } ?: run {
            // если диалоги закончились, переходим к следующей сцене
            currentSceneIndex++
            showNextScene()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}