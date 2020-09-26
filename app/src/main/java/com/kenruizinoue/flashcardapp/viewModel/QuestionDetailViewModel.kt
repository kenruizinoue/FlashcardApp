package com.kenruizinoue.flashcardapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kenruizinoue.flashcardapp.model.DataRepository
import com.kenruizinoue.flashcardapp.model.QuestionCard
import kotlinx.coroutines.launch
import javax.inject.Inject

class QuestionDetailViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel() {

    fun getQuestionById(id: Int): LiveData<QuestionCard> = dataRepository.getCardById(id)

    fun startUpdate(id: Int, question: String, answer: String): Boolean {
        if (!(question.isNotBlank() && answer.isNotBlank())) return false
        viewModelScope.launch {
            dataRepository.updateQuestionCard(QuestionCard(id, question, answer))
        }
        return true
    }

    fun startDelete(id: Int) {
        viewModelScope.launch { dataRepository.deleteById(id) }
    }
}