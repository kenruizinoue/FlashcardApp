package com.kenruizinoue.flashcardapp.model

import androidx.lifecycle.LiveData

class DataRepository(private val questionCardDao: QuestionCardDao) {

    suspend fun insertQuestionCard(questionCard: QuestionCard) {
        questionCardDao.insertCard(questionCard)
    }

    suspend fun getCards(): LiveData<List<QuestionCard>> {
        return questionCardDao.getCards()
    }

}