package com.kenruizinoue.flashcardapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kenruizinoue.flashcardapp.model.DataRepository
import com.kenruizinoue.flashcardapp.model.QuestionCard
import javax.inject.Inject

class QuestionListViewModel @Inject constructor(private val dataRepository: DataRepository): ViewModel() {

    fun getUsers(): LiveData<List<QuestionCard>> {
        return dataRepository.getCards()
    }
}