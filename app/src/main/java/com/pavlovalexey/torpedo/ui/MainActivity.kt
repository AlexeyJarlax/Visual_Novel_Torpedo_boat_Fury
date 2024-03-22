package com.pavlovalexey.torpedo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.viewmodel.GameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val gameViewModel: GameViewModel by viewModel()

    private lateinit var dialogueTextView: TextView
    private lateinit var optionsLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dialogueTextView = findViewById(R.id.dialogueTextView)
        optionsLayout = findViewById(R.id.optionsLayout)

        // Показываем начальный диалог
        updateUI()

        // Добавляем обработчик нажатия на экран для перехода к следующему диалогу без выбора пользователя
        findViewById<View>(android.R.id.content).setOnClickListener {
            gameViewModel.goToNextDialogue()
            updateUI()
        }
    }

    // Обновление пользовательского интерфейса на основе текущего диалога
    private fun updateUI() {
        val currentDialogue = gameViewModel.getCurrentDialogue()
        dialogueTextView.text = currentDialogue.text
        optionsLayout.removeAllViews()

        currentDialogue.options.forEachIndexed { index, option ->
            val optionButton = Button(this)
            optionButton.text = option.text
            optionButton.setOnClickListener {
                gameViewModel.selectOption(index)
                updateUI()
            }
            optionsLayout.addView(optionButton)
        }
    }
}
