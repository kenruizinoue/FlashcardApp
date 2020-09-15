package com.kenruizinoue.flashcardapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class QuestionCard(
    @PrimaryKey(autoGenerate = true)
    val cardId: Int? = null,
    val question: String,
    val answer: String
)