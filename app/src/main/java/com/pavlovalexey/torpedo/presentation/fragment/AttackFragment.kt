package com.pavlovalexey.torpedo.presentation.fragment

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
import com.pavlovalexey.torpedo.presentation.adapter.AdapterLower
import com.pavlovalexey.torpedo.presentation.adapter.AdapterUpper
import com.pavlovalexey.torpedo.presentation.viewmodel.AttackViewModel

class AttackFragment : Fragment() {

    private val viewModel: AttackViewModel by viewModels()
    private lateinit var progressBar: ProgressBar
    private lateinit var leftValueTextView: TextView
    private lateinit var rightValueTextView: TextView
    private lateinit var upperRecyclerView: RecyclerView
    private lateinit var upperAdapter: AdapterUpper
    private lateinit var lowerAdapter: AdapterLower

    private var leftValue: Int = 100
    private var rightValue: Int = 0

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
        upperAdapter = AdapterUpper(leftValue, rightValue) { card, position ->
            upperAdapter.removeItem(position)
            lowerAdapter.addItem(card)
            leftValue += (leftValue * 0.1).toInt()
            updateProgressBar()
        }
        upperRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        upperRecyclerView.adapter = upperAdapter
        upperAdapter.setProgressBar(progressBar)

        // Настройка нижнего RecyclerView
        val lowerRecyclerView = view.findViewById<RecyclerView>(R.id.lowerRecyclerView)
        lowerAdapter = AdapterLower(leftValue, rightValue) { card, position ->
            if (card.title.isNotEmpty() && card.emoji.isNotEmpty()) {
                lowerAdapter.removeItem(position)
                upperAdapter.addItem(card)
                leftValue += (leftValue * 0.1).toInt()
                updateProgressBar()
            } else {
                toast("Карточка не содержит значений и не будет отображаться")
            }
        }
        lowerRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        lowerRecyclerView.adapter = lowerAdapter

        // Устанавливаем прогресс
        updateProgressBar()

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

        val powerTextView = view.findViewById<TextView>(R.id.powerTextView)
        leftValueTextView.text = leftValue.toString()
        if (rightValue == 0) {
            progressBar.visibility = View.GONE
            powerTextView.text = "соотношение сил неизвестно"
            rightValueTextView.text = "???"
        } else {
            progressBar.visibility = View.VISIBLE
            rightValueTextView.text = rightValue.toString()
        }

        return view
    }

    private fun updateProgressBar() {
        val difference = leftValue - rightValue
        val progressValue = (difference.coerceIn(0, 100) * 100) / 100
        progressBar.progress = progressValue
    }

    private fun toast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}