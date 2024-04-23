package com.pavlovalexey.torpedo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.model.Card

class AdapterLower(private val onItemClick: (Card) -> Unit) : RecyclerView.Adapter<AdapterLower.ViewHolder>() {

    companion object {
        val cards = mutableListOf<Card>()
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val emojiTextView: TextView = itemView.findViewById(R.id.emojiTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = AdapterUpper.cards[position] // Используем карточки из YourAdapterUpper
                    onItemClick(item)
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
        // Теперь onBindViewHolder будет пустым, так как карточки будут отображаться в YourAdapterUpper
    }

    override fun getItemCount(): Int {
        return AdapterUpper.cards.size // Используем размер списка карточек из YourAdapterUpper
    }

    fun removeItem(position: Int) {
        AdapterUpper.cards.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addItem(card: Card) {
        AdapterUpper.cards.add(card)
        notifyItemInserted(AdapterUpper.cards.size - 1)
    }
}
