package com.kenruizinoue.flashcardapp.di

import android.app.Application
import android.content.Context
import com.kenruizinoue.flashcardapp.view.fragment.QuestionDetailFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [RoomModule::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance applicationContext: Application): AppComponent
    }

    fun inject(questionDetailFragment: QuestionDetailFragment)
}