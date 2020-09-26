package com.kenruizinoue.flashcardapp.viewModel

import android.content.res.Resources
import android.view.View
import androidx.lifecycle.*
import com.google.android.material.snackbar.Snackbar
import com.kenruizinoue.flashcardapp.R
import com.kenruizinoue.flashcardapp.model.DataRepository
import com.kenruizinoue.flashcardapp.model.QuestionCard
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddQuestionViewModel @Inject constructor(private val dataRepository: DataRepository) : ViewModel(){

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
        dataRepository.insertQuestionCard(card)
    }
}