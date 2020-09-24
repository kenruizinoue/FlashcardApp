package com.kenruizinoue.flashcardapp.model

import androidx.lifecycle.LiveData
import androidx.room.*
import javax.inject.Singleton

@Singleton
@Dao
interface QuestionCardDao {
    @Insert
    suspend fun insertCard(card: QuestionCard)

    @Query("SELECT * FROM QuestionCard")
    fun getCards(): LiveData<List<QuestionCard>>

    @Query("SELECT * FROM QuestionCard")
    suspend fun getCardList(): List<QuestionCard>

    @Query("SELECT * FROM QuestionCard WHERE cardId=:id")
    fun getCardById(id: Int): LiveData<QuestionCard>

    @Update
    suspend fun updateQuestionCard(questionCard: QuestionCard)

    @Query("DELETE FROM QuestionCard WHERE cardId = :id")
    suspend fun deleteById(id: Int)
}