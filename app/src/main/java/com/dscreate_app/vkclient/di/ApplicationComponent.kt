package com.dscreate_app.vkclient.di

import android.content.Context
import com.dscreate_app.vkclient.domain.entities.FeedPost
import com.dscreate_app.vkclient.presentation.screens.main.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun getCommentsScreenComponentFactory():CommentsScreenComponent.Factory

    @Component.Factory
    interface Factory {

        fun create(
           @BindsInstance context: Context
        ): ApplicationComponent
    }
}