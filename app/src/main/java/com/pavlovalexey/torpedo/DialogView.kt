package com.pavlovalexey.torpedo

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import com.pavlovalexey.torpedo.data.Dialog

class DialogView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var textView: TextView

    init {
        inflate(context, R.layout.view_dialog, this)
        textView = findViewById(R.id.textView0)
    }

    fun showNextDialog(dialog: Dialog) {
        textView.text = "${dialog.characterName}: ${dialog.text}"
    }
}