package com.dscreate_app.vkclient.di

import androidx.lifecycle.ViewModel
import com.dscreate_app.vkclient.presentation.screens.comments.view_model.CommentsViewModel
import com.dscreate_app.vkclient.presentation.screens.main.view_model.MainViewModel
import com.dscreate_app.vkclient.presentation.screens.news.view_model.NewsFeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(NewsFeedViewModel::class)
    @Binds
    fun bindNewsFeedViewModel(viewModel: NewsFeedViewModel): ViewModel

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @IntoMap
    @ViewModelKey(CommentsViewModel::class)
    @Binds
    fun bindCommentsViewModel(viewModel: CommentsViewModel): ViewModel
}