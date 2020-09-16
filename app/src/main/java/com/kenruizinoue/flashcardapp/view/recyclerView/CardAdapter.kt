package com.kenruizinoue.flashcardapp.view.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kenruizinoue.flashcardapp.model.QuestionCard

class CardAdapter(private var data: ArrayList<QuestionCard>) : RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CardViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val questionCard = data[position]
        holder.bind(questionCard)
    }

    override fun getItemCount(): Int = data.size

    fun updateCards(cards: List<QuestionCard>) {
        data = cards as ArrayList<QuestionCard>
        notifyDataSetChanged()
    }
}