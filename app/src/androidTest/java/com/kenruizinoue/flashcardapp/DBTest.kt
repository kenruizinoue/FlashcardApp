package com.kenruizinoue.flashcardapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kenruizinoue.flashcardapp.model.QuestionCard
import com.kenruizinoue.flashcardapp.model.QuestionCardDao
import com.kenruizinoue.flashcardapp.model.QuestionCardDatabase
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.IsEqual.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class DBTest {
    private lateinit var questionCardDao: QuestionCardDao
    private lateinit var db: QuestionCardDatabase

    @Before
    fun createDB() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, QuestionCardDatabase::class.java).build()
        questionCardDao = db.questionCardDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDB() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun writeAndReadData() {
        runBlocking {
            questionCardDao.insertCard(QuestionCard(question = "question 1", answer = "answer 1"))
            questionCardDao.insertCard(QuestionCard(question = "question 2", answer = "answer 2"))
            questionCardDao.insertCard(QuestionCard(question = "question 3", answer = "answer 3"))
            val list = questionCardDao.getCardList()
            assertThat(list.size, equalTo(3))
        }
    }

}