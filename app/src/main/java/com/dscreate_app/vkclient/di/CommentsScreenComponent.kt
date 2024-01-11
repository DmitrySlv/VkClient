package com.dscreate_app.vkclient.di

import com.dscreate_app.vkclient.domain.entities.FeedPost
import com.dscreate_app.vkclient.presentation.screens.ViewModelFactory
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        CommentsViewModelModule::class
    ]
)
interface CommentsScreenComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance feedPost: FeedPost
        ):CommentsScreenComponent
    }
}