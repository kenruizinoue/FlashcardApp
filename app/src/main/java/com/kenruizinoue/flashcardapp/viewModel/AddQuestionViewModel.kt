package com.kenruizinoue.flashcardapp.viewModel

import android.app.Application
import android.content.res.Resources
import android.view.View
import androidx.lifecycle.*
import com.google.android.material.snackbar.Snackbar
import com.kenruizinoue.flashcardapp.R
import com.kenruizinoue.flashcardapp.model.DataRepository
import com.kenruizinoue.flashcardapp.model.QuestionCard
import com.kenruizinoue.flashcardapp.model.QuestionCardDatabase
import kotlinx.coroutines.launch

class AddQuestionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DataRepository

    init {
        val questionCardDao = QuestionCardDatabase
            .getQuestionCardDB(application)
            .questionCardDao()
        repository = DataRepository(questionCardDao)
    }

    fun startInsert(questionString: String, answerString: String, view: View, resources: Resources): Boolean {
        return if (questionString.isNotBlank() && answerString.isNotBlank()) {
            insertCard(QuestionCard(question = questionString, answer = answerString))
            true
        } else {
            Snackbar.make(view, resources.getString(R.string.empty_message), Snackbar.LENGTH_LONG).show()
            false
        }
    }

    private fun insertCard(card: QuestionCard) = viewModelScope.launch {
        repository.insertQuestionCard(card)
    }
}