package com.pavlovalexey.torpedo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pavlovalexey.torpedo.R
import com.pavlovalexey.torpedo.model.Card

class YourAdapterLower(private val itemList: MutableList<Card>, private val onItemClick: (Card) -> Unit) : RecyclerView.Adapter<YourAdapterLower.YourViewHolder>() {

    class ViewHolder(itemView: View, private val onItemClick: (Card) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val emojiTextView: TextView = itemView.findViewById(R.id.emojiTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = itemList[position]
                    onItemClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        return ViewHolder(view, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.titleTextView.text = currentItem.title
        holder.emojiTextView.text = currentItem.emoji
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun removeItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class YourViewHolder(itemView: View, private val onItemClick: (Card) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        // Остальной код ViewHolder
    }
}

