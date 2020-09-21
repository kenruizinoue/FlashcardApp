package com.kenruizinoue.flashcardapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenruizinoue.flashcardapp.model.QuestionCard
import com.kenruizinoue.flashcardapp.model.QuestionCardDao
import kotlinx.coroutines.launch

class QuestionDetailViewModel(private val questionCardDao: QuestionCardDao) : ViewModel() {

    fun getQuestionById(id: Int): LiveData<QuestionCard> = questionCardDao.getCardById(id)

    fun startUpdate(id: Int, question: String, answer: String): Boolean {
        if (!(question.isNotBlank() && answer.isNotBlank())) return false
        viewModelScope.launch {
            questionCardDao.updateQuestionCard(QuestionCard(id, question, answer))
        }
        return true
    }

    fun startDelete(id: Int) {
        viewModelScope.launch { questionCardDao.deleteById(id) }
    }
}