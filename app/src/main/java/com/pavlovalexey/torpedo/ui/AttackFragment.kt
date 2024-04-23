package com.pavlovalexey.torpedo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.model.Card
import com.pavlovalexey.torpedo.model.cards
import com.pavlovalexey.torpedo.viewmodel.AttackViewModel
import timber.log.Timber

class AttackFragment : Fragment() {

    private val viewModel: AttackViewModel by viewModels()

    private lateinit var progressBar: ProgressBar
    private lateinit var leftValueTextView: TextView
    private lateinit var rightValueTextView: TextView

    private lateinit var upperRecyclerView: RecyclerView
    private lateinit var lowerRecyclerView: RecyclerView
    private lateinit var upperAdapter: YourAdapterUpper
    private lateinit var lowerAdapter: YourAdapterLower

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_attack, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        leftValueTextView = view.findViewById(R.id.attack_text_view_left)
        rightValueTextView = view.findViewById(R.id.attack_text_view_right)

        // Настройка верхнего RecyclerView
        upperRecyclerView = view.findViewById(R.id.upperRecyclerView)
        upperAdapter = YourAdapterUpper()
        upperRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        upperRecyclerView.adapter = upperAdapter

        // Настройка нижнего RecyclerView
        lowerRecyclerView = view.findViewById(R.id.lowerRecyclerView)
        lowerAdapter = YourAdapterLower(cards.toMutableList()) { card ->
            // Удаление карточки из списка нижнего адаптера
            lowerAdapter.removeItem(adapterPosition)

            // Добавление карточки в верхний адаптер
            upperAdapter.addItem(card)
        }
        lowerRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        lowerRecyclerView.adapter = lowerAdapter

        val leftValue = 50
        val rightValue = 50

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
            val fragmentManager = parentFragmentManager
            fragmentManager.popBackStack()
        }

        val attackCloseButton = view.findViewById<Button>(R.id.attackCloseButton)
        attackCloseButton.setOnClickListener {
            toast("Функция ПОБЕГ в разработке...")
            toast("Сражайся, тряпка!")
        }
        return view
    }

    private fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}