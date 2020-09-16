package com.kenruizinoue.flashcardapp.view.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kenruizinoue.flashcardapp.R
import com.kenruizinoue.flashcardapp.model.QuestionCard

class CardViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_question_card, parent, false)) {
    
    var answerTextView: TextView = itemView.findViewById(R.id.answerTextView)
    var questionTextView: TextView = itemView.findViewById(R.id.questionTextView)
    
    fun bind(questionCard: QuestionCard) {
        answerTextView.text = questionCard.answer
        questionTextView.text = questionCard.question
    }
}