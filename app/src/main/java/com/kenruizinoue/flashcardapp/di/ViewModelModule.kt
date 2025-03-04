package com.kenruizinoue.flashcardapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kenruizinoue.flashcardapp.viewModel.AddQuestionViewModel
import com.kenruizinoue.flashcardapp.viewModel.PracticeViewModel
import com.kenruizinoue.flashcardapp.viewModel.QuestionDetailViewModel
import com.kenruizinoue.flashcardapp.viewModel.QuestionListViewModel
import com.kenruizinoue.flashcardapp.viewModel.factory.ViewModelFactory
import com.kenruizinoue.flashcardapp.viewModel.factory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(QuestionDetailViewModel::class)
    internal abstract fun questionDetailViewModel(viewModel: QuestionDetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddQuestionViewModel::class)
    internal abstract fun addQuestionViewModel(viewModel: AddQuestionViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PracticeViewModel::class)
    internal abstract fun practiceViewModel(viewModel: PracticeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QuestionListViewModel::class)
    internal abstract fun questionListViewModel(viewModel: QuestionListViewModel): ViewModel
}