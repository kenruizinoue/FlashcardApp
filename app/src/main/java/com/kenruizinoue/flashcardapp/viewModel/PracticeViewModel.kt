package com.kenruizinoue.flashcardapp.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.kenruizinoue.flashcardapp.model.DataRepository
import com.kenruizinoue.flashcardapp.model.QuestionCard
import com.kenruizinoue.flashcardapp.model.QuestionCardDatabase
import kotlinx.coroutines.launch

class PracticeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DataRepository
    private lateinit var dataInRandomOrder: List<QuestionCard>
    private var questionPos = 0
    var isAnswerTextVisible: MutableLiveData<Boolean> = MutableLiveData(false)
    var actualQuestionCard: MutableLiveData<QuestionCard> = MutableLiveData(null)

    init {
        val questionCardDao = QuestionCardDatabase
            .getQuestionCardDB(application)
            .questionCardDao()
        repository = DataRepository(questionCardDao)
    }

    fun fetchQuestionCards() {
        viewModelScope.launch {
            val data = repository.getCardList()
            dataInRandomOrder = data.shuffled()
            if (dataInRandomOrder.isNotEmpty()) actualQuestionCard.value = dataInRandomOrder[0]
        }
    }

    private fun toggleVisibility () {
        isAnswerTextVisible.value = !isAnswerTextVisible.value!!
    }

    private fun initializeQuestions() {
        dataInRandomOrder = dataInRandomOrder.shuffled()
        questionPos = 0
    }

    fun fabPressed() {
        if (isAnswerTextVisible.value!!) {
            if (dataInRandomOrder.isNotEmpty()) actualQuestionCard.value = dataInRandomOrder[questionPos++]
            // if the pos reaches data length, start again
            if (questionPos == dataInRandomOrder.size) initializeQuestions()
            toggleVisibility()
        } else {
            toggleVisibility()
        }
    }
}