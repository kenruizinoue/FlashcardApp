package com.kenruizinoue.flashcardapp.view.recyclerView

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kenruizinoue.flashcardapp.R
import com.kenruizinoue.flashcardapp.model.QuestionCard
import com.kenruizinoue.flashcardapp.view.fragment.ItemClickListener
import kotlin.properties.Delegates

class CardViewHolder(inflatedView: View, private val listener: ItemClickListener) :
    RecyclerView.ViewHolder(inflatedView),
    View.OnClickListener {

    var answerTextView: TextView = itemView.findViewById(R.id.answerTextView)
    var questionTextView: TextView = itemView.findViewById(R.id.questionTextView)
    private var questionId by Delegates.notNull<Int>()

    init {
        inflatedView.setOnClickListener(this)
    }

    fun bind(questionCard: QuestionCard) {
        answerTextView.text = questionCard.answer
        questionTextView.text = questionCard.question
        questionId = questionCard.cardId!!
    }

    override fun onClick(view: View?) {
        listener.onItemClicked(questionId)
    }
}