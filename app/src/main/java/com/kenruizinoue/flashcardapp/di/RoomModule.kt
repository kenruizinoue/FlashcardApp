package com.kenruizinoue.flashcardapp.di

import android.app.Application
import android.content.Context
import com.kenruizinoue.flashcardapp.model.QuestionCardDao
import com.kenruizinoue.flashcardapp.model.QuestionCardDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    fun getQuestionCardDatabase(application: Application): QuestionCardDatabase {
        return QuestionCardDatabase.getQuestionCardDB(application)
    }

    @Provides
    fun getQuestionCardDao(questionCardDatabase: QuestionCardDatabase): QuestionCardDao {
        return questionCardDatabase.questionCardDao()
    }

}
