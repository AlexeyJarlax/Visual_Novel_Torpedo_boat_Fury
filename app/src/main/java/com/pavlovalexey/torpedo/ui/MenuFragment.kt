package com.pavlovalexey.torpedo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pavlovalexey.torpedo.R

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        val soundButton = view.findViewById<Button>(R.id.soundButton)
        val autoPlayButton = view.findViewById<Button>(R.id.autoPlayButton)
        val closeSaveButton = view.findViewById<Button>(R.id.closeSaveButton)
        val menuLayout = view.findViewById<View>(R.id.fragment_menu)
        (activity as MainActivity).hideOptionsLayout()

        soundButton.setOnClickListener {
            Toast.makeText(requireContext(), "Вы нажали кнопку 'Звук'", Toast.LENGTH_SHORT).show()
        }

        autoPlayButton.setOnClickListener {
            Toast.makeText(requireContext(), "Вы нажали кнопку 'Автоплей'", Toast.LENGTH_SHORT).show()
        }

        closeSaveButton.setOnClickListener {
            Toast.makeText(requireContext(), "Вы нажали кнопку 'Закрыть + Сохранить Прогресс'", Toast.LENGTH_SHORT).show()
        }

        menuLayout.setOnClickListener {
            (activity as MainActivity).showOptionsLayout()
            parentFragmentManager.beginTransaction().remove(this).commit()
        }

        return view
    }
}