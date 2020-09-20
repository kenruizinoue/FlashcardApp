package com.kenruizinoue.flashcardapp.view.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kenruizinoue.flashcardapp.R
import com.kenruizinoue.flashcardapp.model.QuestionCard
import com.kenruizinoue.flashcardapp.view.fragment.ItemClickListener

class CardAdapter(private var data: ArrayList<QuestionCard>, private val listener: ItemClickListener) :
    RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_question_card, parent, false)
        return CardViewHolder(inflatedView, listener)
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