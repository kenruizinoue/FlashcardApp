package com.kenruizinoue.flashcardapp.di

import android.app.Application
import com.kenruizinoue.flashcardapp.view.fragment.AddQuestionFragment
import com.kenruizinoue.flashcardapp.view.fragment.PracticeFragment
import com.kenruizinoue.flashcardapp.view.fragment.QuestionDetailFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class, ViewModelModule::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance applicationContext: Application): AppComponent
    }

    fun inject(questionDetailFragment: QuestionDetailFragment)
    fun inject(addQuestionFragment: AddQuestionFragment)
    fun inject(practiceFragment: PracticeFragment)
}