package com.kenruizinoue.flashcardapp.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.kenruizinoue.flashcardapp.model.DataRepository
import com.kenruizinoue.flashcardapp.model.QuestionCard
import com.kenruizinoue.flashcardapp.model.QuestionCardDatabase
import kotlinx.coroutines.launch

class AddQuestionViewModel(application: Application): AndroidViewModel(application) {
    val question = MutableLiveData<String>()
    val answer = MutableLiveData<String>()

    private val repository: DataRepository

    init {
        val questionCardDao = QuestionCardDatabase
            .getQuestionCardDB(application)
            .questionCardDao()
        repository = DataRepository(questionCardDao)
    }

    // validate fields and create QuestionCard object
    fun startInsert() {
        insertCard(QuestionCard(question = "question", answer = "answer"))
    }

    private fun insertCard(card: QuestionCard) = viewModelScope.launch {
        repository.insertQuestionCard(card)
    }
}