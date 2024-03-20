package com.pavlovalexey.torpedo

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.pavlovalexey.torpedo.data.Scene

class SceneView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var imageView: ImageView
    private lateinit var textView: TextView

    init {
        inflate(context, R.layout.view_scene, this)
        imageView = findViewById(R.id.imageView0)
        textView = findViewById(R.id.textView0)
    }

    fun showScene(scene: Scene) {
        imageView.setImageResource(scene.image)
        // логика для воспроизведения музыки
    }

    fun showText(text: String) {
        textView.text = text
    }
}