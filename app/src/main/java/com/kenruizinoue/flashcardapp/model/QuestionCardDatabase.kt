package com.kenruizinoue.flashcardapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [QuestionCard::class])
abstract class QuestionCardDatabase: RoomDatabase() {
    abstract fun questionCardDao(): QuestionCardDao

    companion object {
        @Volatile
        private var INSTANCE: QuestionCardDatabase? = null

        fun getQuestionCardDB(context: Context): QuestionCardDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        QuestionCardDatabase::class.java,
                        "question_card_db"
                    ).build()
                INSTANCE = instance
                return instance
            }
        }

        fun destroyDB() {
            INSTANCE = null
        }
    }
}