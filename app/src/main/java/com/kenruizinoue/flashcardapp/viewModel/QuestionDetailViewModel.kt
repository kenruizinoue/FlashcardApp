package com.kenruizinoue.flashcardapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kenruizinoue.flashcardapp.model.QuestionCard
import com.kenruizinoue.flashcardapp.model.QuestionCardDao

class QuestionDetailViewModel(private val questionCardDao: QuestionCardDao) : ViewModel() {

    fun getQuestionById(id: Int): LiveData<QuestionCard> = questionCardDao.getCardById(id)
}