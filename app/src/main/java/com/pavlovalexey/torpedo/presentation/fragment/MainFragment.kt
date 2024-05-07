package com.pavlovalexey.torpedo.presentation.fragment

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.model.Characters
import com.pavlovalexey.torpedo.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : Fragment() {
    private val mainViewModel: MainViewModel by sharedViewModel()

    private lateinit var dialogueTextView: TextView
    private lateinit var optionsLayout: LinearLayout
    private lateinit var sceneImageView: ImageView
    private lateinit var rublesTextView: TextView
    private lateinit var fameTextView: TextView
    private lateinit var teamLoyaltyTextView: TextView
    private lateinit var vodkaTextView: TextView
    private lateinit var maximTextView: TextView
    private lateinit var capitalTextView: TextView
    private lateinit var necronomiconTextView: TextView
    private lateinit var relationshipTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dialogue, container, false)
        initializeViews(root)
        subscribeToViewModel()
        return root
    }

    private fun initializeViews(root: View) {
        dialogueTextView = root.findViewById(R.id.dialogueTextView)
        optionsLayout = root.findViewById(R.id.optionsLayout)
        sceneImageView = root.findViewById(R.id.sceneImageView)
        rublesTextView = root.findViewById(R.id.rublesTextView)
        fameTextView = root.findViewById(R.id.fameTextView)
        teamLoyaltyTextView = root.findViewById(R.id.teamLoyaltyTextView)
        vodkaTextView = root.findViewById(R.id.vodkaTextView)
        maximTextView = root.findViewById(R.id.maximTextView)
        capitalTextView = root.findViewById(R.id.capitalTextView)
        necronomiconTextView = root.findViewById(R.id.necronomiconTextView)
        relationshipTextView = root.findViewById(R.id.relationshipTextView)

        root.findViewById<View>(R.id.resourcesLayout).setOnClickListener {
            findNavController().navigate(R.id.action_dialogueFragment_to_menuFragment)
        }

        root.findViewById<View>(R.id.resourcesLayout2).setOnClickListener {
            findNavController().navigate(R.id.action_dialogueFragment_to_menuFragment)
        }

        sceneImageView.setOnClickListener {
            if (optionsLayout.childCount == 0 && childFragmentManager.findFragmentByTag("MenuFragment") == null && childFragmentManager.findFragmentByTag("AttackFragment") == null) {
                mainViewModel.goToNextDialogue()
            } else {
            }
        }
    }

    private fun subscribeToViewModel() {
        mainViewModel.currentScene.observe(viewLifecycleOwner, Observer { scene ->
            sceneImageView.setImageResource(scene.background)
        })

        mainViewModel.currentDialogueIndex.observe(viewLifecycleOwner, Observer { index ->
            updateUI(index)
        })

        mainViewModel.initialResources.observe(viewLifecycleOwner, Observer { resources ->
            // Инициализация представления с использованием начальных ресурсов
            // Обновите интерфейс, используя загруженные ресурсы
            rublesTextView.text = getString(R.string.currency_format, "₽", resources.rubles)
            fameTextView.text = getString(R.string.symbol_format, "🏆", resources.fame)
            teamLoyaltyTextView.text = getString(R.string.symbol_format, "🚩", resources.teamLoyalty)
            vodkaTextView.text = getString(R.string.symbol_format, "🍶", resources.vodka)
            maximTextView.text = getString(R.string.symbol_format, "💥", resources.maxim)
            capitalTextView.text = getString(R.string.symbol_format, "☭", resources.capital)
            necronomiconTextView.text = getString(R.string.symbol_format, "🐙", resources.necronomicon)

            when (resources.relationship) {
                -2 -> relationshipTextView.text = "😡"
                -1 -> relationshipTextView.text = "😠"
                0 -> relationshipTextView.text = ""
                1 -> relationshipTextView.text = "🙂"
                2 -> relationshipTextView.text = "😊"
                else -> relationshipTextView.text = ""
            }
        })
    }

    private fun updateUI(currentDialogueIndex: Int) {
        val currentDialogue =
            mainViewModel.gameRepository.getDialogueByIndex(currentDialogueIndex) ?: return

        if (currentDialogueIndex == 0) {
            mainViewModel.resetResources()
        }

        val partsColon = currentDialogue.text?.split("::")
        val partsDash = currentDialogue.text?.split("--")
        val parts = if (partsColon != null && partsColon.size == 2) {
            partsColon
        } else if (partsDash != null && partsDash.size == 2) {
            partsDash
        } else {
            null
        }

        val formattedText = if (parts != null && parts.size == 2) {
            val highlightedText = parts[0] // Текст, который нужно подчеркнуть
            val remainingText = parts[1] // Оставшаяся часть текста

            SpannableStringBuilder().apply {
                append(highlightedText)
                setSpan(
                    StyleSpan(Typeface.ITALIC), // Установка стиля курсива
                    0, highlightedText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                setSpan(
                    ForegroundColorSpan(
                        ContextCompat.getColor(
                            requireContext(), R.color.aquamarine
                        )
                    ), 0, highlightedText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                append(remainingText)
            }
        } else {
            currentDialogue.text ?: ""
        }
        dialogueTextView.text = formattedText

        // Установка значений ресурсов
        val resources = mainViewModel.resources.value
        resources?.let {
            if (it.rubles != 0) {
                animateTextChange(
                    rublesTextView, getString(R.string.currency_format, "₽", it.rubles)
                )
            } else {
                rublesTextView.text = ""
            }

            if (it.fame != 0) {
                animateTextChange(fameTextView, getString(R.string.symbol_format, "🏆", it.fame))
            } else {
                fameTextView.text = ""
            }

            if (it.teamLoyalty != 0) {
                animateTextChange(
                    teamLoyaltyTextView, getString(R.string.symbol_format, "🚩", it.teamLoyalty)
                )
            } else {
                teamLoyaltyTextView.text = ""
            }

            if (it.vodka != 0) {
                animateTextChange(vodkaTextView, getString(R.string.symbol_format, "🍶", it.vodka))
            } else {
                vodkaTextView.text = ""
            }

            if (it.maxim != 0) {
                animateTextChange(maximTextView, getString(R.string.symbol_format, "💥", it.maxim))
            } else {
                maximTextView.text = ""
            }

            if (it.capital != 0) {
                animateTextChange(
                    capitalTextView, getString(R.string.symbol_format, "☭", it.capital)
                )
            } else {
                capitalTextView.text = ""
            }

            if (it.necronomicon != 0) {
                animateTextChange(
                    necronomiconTextView, getString(R.string.symbol_format, "🐙", it.necronomicon)
                )
            } else {
                necronomiconTextView.text = ""
            }

            when (it.relationship) {
                -2 -> animateTextChange(relationshipTextView, "😡")
                -1 -> animateTextChange(relationshipTextView, "😠")
                0 -> relationshipTextView.text = ""
                1 -> animateTextChange(relationshipTextView, "🙂")
                2 -> animateTextChange(relationshipTextView, "😊")
                else -> relationshipTextView.text = ""
            }
        }

        optionsLayout.removeAllViews()
        currentDialogue.options.forEachIndexed { index, option ->
            val optionButtonView =
                LayoutInflater.from(requireContext()).inflate(R.layout.option_button, optionsLayout, false)
            if (optionButtonView is MaterialButton) {
                optionButtonView.text = option.text
                optionButtonView.setOnClickListener {
                    mainViewModel.selectOption(index)
                    when (option.text) {
                        Characters.attack -> {
                            findNavController().navigate(R.id.action_dialogueFragment_to_attackFragment)
                        }
                        else -> {
                        }
                    }
                }
                optionsLayout.addView(optionButtonView)
            }
        }
    }



    private fun animateTextChange(textView: TextView, newText: String) {
        if (textView.text.toString() != newText) {
            val animator = ValueAnimator.ofFloat(1f, 2f)
            animator.duration = 400
            animator.addUpdateListener { animation ->
                val animatedValue = animation.animatedValue as Float
                textView.scaleX = animatedValue
                textView.scaleY = animatedValue
                if (animatedValue == 2f) {
                    textView.text = newText
                }
            }

            val reverseAnimator = ValueAnimator.ofFloat(2f, 1f)
            reverseAnimator.duration = 400
            reverseAnimator.addUpdateListener { animation ->
                val animatedValue = animation.animatedValue as Float
                textView.scaleX = animatedValue
                textView.scaleY = animatedValue
            }

            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    reverseAnimator.start()
                }
            })

            animator.start()
        }
    }
}