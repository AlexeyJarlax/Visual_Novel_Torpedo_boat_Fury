package com.pavlovalexey.torpedo.presentation
import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.data.Dialog

class DialogView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var textView: TextView
    private lateinit var button1: Button
    private lateinit var button2: Button

    private var dialog: Dialog? = null

    init {
        inflate(context, R.layout.view_dialog, this)
        textView = findViewById(R.id.textView0)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)

        // Установка обработчиков на кнопки
        button1.setOnClickListener { handleChoice(0) }
        button2.setOnClickListener { handleChoice(1) }
    }

    // Метод для отображения диалога
    fun showDialog(dialog: Dialog) {
        this.dialog = dialog
        textView.text = "${dialog.characterName}${dialog.text}"

        // Отображение кнопок выбора, если они доступны
        if (dialog.choices != null) {
            button1.text = dialog.choices[0]
            button2.text = dialog.choices[1]
            button1.visibility = VISIBLE
            button2.visibility = VISIBLE
        } else {
            button1.visibility = GONE
            button2.visibility = GONE
        }
    }

    // Метод для обработки выбора пользователя
    private fun handleChoice(choiceIndex: Int) {
        val chosenChoice = dialog?.choices?.getOrNull(choiceIndex)
        // Обработка выбора...
    }
}