package com.kenruizinoue.flashcardapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuestionCardDao {
    @Insert
    suspend fun insertCard(card: QuestionCard)

    @Query("SELECT * FROM QuestionCard")
    fun getCards(): LiveData<List<QuestionCard>>

    @Query("SELECT * FROM QuestionCard")
    suspend fun getCardList(): List<QuestionCard>
}