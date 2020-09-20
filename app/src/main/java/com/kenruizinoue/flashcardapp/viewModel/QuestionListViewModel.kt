package com.kenruizinoue.flashcardapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.kenruizinoue.flashcardapp.model.DataRepository
import com.kenruizinoue.flashcardapp.model.QuestionCard
import com.kenruizinoue.flashcardapp.model.QuestionCardDatabase

class QuestionListViewModel (application: Application): AndroidViewModel(application) {
    private val repository: DataRepository

    init {
        val questionCardDao = QuestionCardDatabase
            .getQuestionCardDB(application)
            .questionCardDao()
        repository = DataRepository(questionCardDao)
    }

    fun getUsers(): LiveData<List<QuestionCard>> {
        return repository.getCards()
    }
}