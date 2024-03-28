package com.pavlovalexey.torpedo.ui

/**
 * В этом коде реализована логика для управления сюжетом Visual Novel "Torpedo Boat Grozny by Pavlov Alexey.
 *
 * Основные компоненты кода:
 *
 *** Репозиторий игры (GameRepositoryImpl): Этот компонент содержит данные игры, такие как диалоги и сцены.
 *      - Методы:
 *          - `getInitialDialogue()`: Возвращает начальный диалог.
 *          - `getDialogueByIndex(index: Int)`: Возвращает диалог по указанному индексу.
 *          - `getInitialScene()`: Возвращает начальную сцену.
 *          - `getSceneByDialogueIndex(dialogueIndex: Int)`: Возвращает сцену для указанного индекса диалога.
 *
 *** Диалог (Dialogue): Представляет объект-диалог в игре.
 *      - Содержит текст и варианты выбора.
 *
 *** Вариант выбора из события (Option): Представляет вариант, доступный для выбора в диалоге.
 *      - Содержит текст, индекс следующего диалога и эффекты ресурсов.
 *
 *** Ресурсы (Resource): Представляет объект-ресурсы.
 *      - Включает в себя Царские рубли, славу, лояльность команды, водку, тяжелое автоматическое оружие.
 *
 *** Сцена (Scene): Представляет объект-сцену. Это изображение и описание сцены, отображаемой на экране за диалогами.
 *
 *** Управление визуальной моделью (GameViewModel): Отвечает за управление бизнес-логикой игры.
 *      - Методы:
 *          - `selectOption(optionIndex: Int)`: Выбирает опцию в диалоге и обновляет ресурсы и сцену.
 *          - `goToNextDialogue()`: Переходит к следующему диалогу без выбора пользователя.
 *
 * MainActivity в приложении Visual Novel "Torpedo Boat Grozny".
 *
 ***Layout activity_main: Основной макет пользовательского интерфейса, завязанный на MainActivity.
 *      - Включает в себя:
 *      текстовое поле для отображения диалогов,
 *      LinearLayout для отображения вариантов выбора,
 *      ImageView для отображения сцены,
 *      поле для рисурсов с символами и значениями,
 *      кнопку меню/выход
 *      если дочитал/ла до сюда то ты супер-красавчик
 */

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.google.android.material.button.MaterialButton
import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.viewmodel.GameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.animation.ScaleAnimation
import androidx.core.view.doOnLayout

class MainActivity : AppCompatActivity() {
    private val gameViewModel: GameViewModel by viewModel()

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dialogueTextView = findViewById(R.id.dialogueTextView)
        optionsLayout = findViewById(R.id.optionsLayout)
        sceneImageView = findViewById(R.id.sceneImageView)
        rublesTextView = findViewById(R.id.rublesTextView)
        fameTextView = findViewById(R.id.fameTextView)
        teamLoyaltyTextView = findViewById(R.id.teamLoyaltyTextView)
        vodkaTextView = findViewById(R.id.vodkaTextView)
        maximTextView = findViewById(R.id.maximTextView)
        capitalTextView = findViewById(R.id.capitalTextView)
        necronomiconTextView = findViewById(R.id.necronomiconTextView)

        gameViewModel.currentScene.observe(this, Observer { scene ->
            sceneImageView.setImageResource(scene.background)
        })

        gameViewModel.currentDialogueIndex.observe(this, Observer { index ->
            updateUI(index)
        })

        findViewById<View>(android.R.id.content).setOnClickListener {
            if (optionsLayout.childCount == 0) { // условие - если кнопок нет то клик по всей вьюхе переключает на следующий диалог
                gameViewModel.goToNextDialogue()
            }

        }
    }

    private fun animateTextChange(textView: TextView, newText: String) {
        if (textView.text.toString() != newText) {
            val animator = ValueAnimator.ofFloat(1f, 2f)
            animator.duration = 400 // Устанавливаем длительность анимации увеличения

            animator.addUpdateListener { animation ->
                val animatedValue = animation.animatedValue as Float
                textView.scaleX = animatedValue
                textView.scaleY = animatedValue
                if (animatedValue == 2f) {
                    textView.text = newText
                }
            }

            val reverseAnimator = ValueAnimator.ofFloat(2f, 1f)
            reverseAnimator.duration = 400 // Устанавливаем длительность анимации уменьшения

            reverseAnimator.addUpdateListener { animation ->
                val animatedValue = animation.animatedValue as Float
                textView.scaleX = animatedValue
                textView.scaleY = animatedValue
            }

            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    reverseAnimator.start() // После завершения анимации увеличения запускаем анимацию уменьшения
                }
            })

            animator.start()
        }
    }

    private fun updateUI(currentDialogueIndex: Int) {
        val currentDialogue =
            gameViewModel.gameRepository.getDialogueByIndex(currentDialogueIndex) ?: return

        // Разделение текста диалога на части до и после "::"
        val parts = currentDialogue.text.split("::")
        val formattedText = if (parts.size == 2) {
            val underlinedText = parts[0] // Текст, который нужно подчеркнуть
            val remainingText = parts[1] // Оставшаяся часть текста

            // Формирование отформатированного текста с подчеркнутой и голубой частью
            SpannableStringBuilder().apply {
                append(underlinedText)
                setSpan(UnderlineSpan(), 0, underlinedText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                setSpan(
                    ForegroundColorSpan(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.yp_blue_light
                        )
                    ), 0, underlinedText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                append(remainingText)
            }
        } else {
            currentDialogue.text // Если "::" нет, просто используем весь текст без подчеркивания и голубого цвета
        }

        dialogueTextView.text = formattedText

        // Установка значений ресурсов
        val resources = gameViewModel.resources.value
        resources?.let {
            if (it.rubles != 0) {
                animateTextChange(rublesTextView, getString(R.string.currency_format, "₽", it.rubles))
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
                    teamLoyaltyTextView,
                    getString(R.string.symbol_format, "🚩", it.teamLoyalty)
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
                animateTextChange(capitalTextView, getString(R.string.symbol_format, "☭", it.capital))
            } else {
                capitalTextView.text = ""
            }

            if (it.necronomicon != 0) {
                animateTextChange(
                    necronomiconTextView,
                    getString(R.string.symbol_format, "🐙", it.necronomicon)
                )
            } else {
                necronomiconTextView.text = ""
            }
        }

        optionsLayout.removeAllViews()
        currentDialogue.options.forEachIndexed { index, option ->
            val optionButtonView =
                LayoutInflater.from(this).inflate(R.layout.option_button, optionsLayout, false)
            if (optionButtonView is MaterialButton) {
                optionButtonView.text = option.text
                optionButtonView.setOnClickListener {
                    gameViewModel.selectOption(index)
                }
                optionsLayout.addView(optionButtonView)
            }
        }
    }
}
