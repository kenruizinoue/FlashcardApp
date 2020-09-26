package com.kenruizinoue.flashcardapp.model

import androidx.lifecycle.LiveData
import javax.inject.Inject

class DataRepository @Inject constructor(private val questionCardDao: QuestionCardDao) {

    suspend fun insertQuestionCard(questionCard: QuestionCard) {
        questionCardDao.insertCard(questionCard)
    }

    fun getCards(): LiveData<List<QuestionCard>> {
        return questionCardDao.getCards()
    }

    suspend fun getCardList(): List<QuestionCard> {
        return questionCardDao.getCardList()
    }

}