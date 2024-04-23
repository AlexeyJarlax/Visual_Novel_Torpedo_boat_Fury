package com.pavlovalexey.torpedo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.model.Card

class AdapterUpper(private var leftValue: Int, private val rightValue: Int, private val onItemClick: (Card, Int) -> Unit) :
    RecyclerView.Adapter<AdapterUpper.ViewHolder>() {

    private val cards = mutableListOf<Card>()
    private lateinit var progressBar: ProgressBar

    fun setProgressBar(progressBar: ProgressBar) {
        this.progressBar = progressBar
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val emojiTextView: TextView = itemView.findViewById(R.id.emojiTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = cards[position]
                    onItemClick(item, position)
                    leftValue += (leftValue * 0.1).toInt()
                    progressBar.progress = calculateProgress()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = cards[position]
        holder.titleTextView.text = currentItem.title
        holder.emojiTextView.text = currentItem.emoji
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    fun addItem(card: Card) {
        cards.add(card)
        notifyItemInserted(cards.size - 1)
    }

    fun removeItem(position: Int) {
        cards.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun calculateProgress(): Int {
        val difference = leftValue - rightValue
        return (difference.coerceIn(0, 100) * 100) / 100
    }
}