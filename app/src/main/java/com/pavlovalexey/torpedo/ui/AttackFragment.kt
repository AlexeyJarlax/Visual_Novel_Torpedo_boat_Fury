package com.pavlovalexey.torpedo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.pavlovalexey.torpedo.R

class AttackFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var leftValueTextView: TextView
    private lateinit var rightValueTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_attack, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        leftValueTextView = view.findViewById(R.id.attack_text_view_left)
        rightValueTextView = view.findViewById(R.id.attack_text_view_right)

        val leftValue = 100
        val rightValue = 1

        leftValueTextView.text = leftValue.toString()
        rightValueTextView.text = rightValue.toString()

        // Вычисляем разницу между значениями
        val difference = leftValue - rightValue

        // Приводим разницу к диапазону прогресса (0-100)
        val progressValue = (difference.coerceIn(0, 100) * 100) / 100

        // Устанавливаем прогресс
        progressBar.progress = progressValue

        val attackPlayButton = view.findViewById<Button>(R.id.attackPlayButton)
        attackPlayButton.setOnClickListener {
            // При нажатии на кнопку "Автоплей" можно добавить сюда логику, если нужно
        }

        val attackCloseButton = view.findViewById<Button>(R.id.attackCloseButton)
        attackCloseButton.setOnClickListener {
            // Закрываем фрагмент при нажатии на кнопку "Выход"
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        return view
    }
}
