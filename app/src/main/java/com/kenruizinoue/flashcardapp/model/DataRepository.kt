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

    fun getCardById(id: Int): LiveData<QuestionCard> {
        return questionCardDao.getCardById(id)
    }

    suspend fun updateQuestionCard(questionCard: QuestionCard) {
        questionCardDao.updateQuestionCard(questionCard)
    }

    suspend fun deleteById(id: Int) {
        questionCardDao.deleteById(id)
    }

    suspend fun getCardList(): List<QuestionCard> {
        return questionCardDao.getCardList()
    }

}