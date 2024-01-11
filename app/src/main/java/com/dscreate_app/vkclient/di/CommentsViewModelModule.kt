package com.dscreate_app.vkclient.di

import androidx.lifecycle.ViewModel
import com.dscreate_app.vkclient.presentation.screens.comments.view_model.CommentsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CommentsViewModelModule {

    @IntoMap
    @ViewModelKey(CommentsViewModel::class)
    @Binds
    fun bindCommentsViewModel(viewModel: CommentsViewModel): ViewModel
}