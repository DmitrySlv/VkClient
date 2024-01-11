package com.dscreate_app.vkclient.presentation.screens

import android.app.Application
import com.dscreate_app.vkclient.di.ApplicationComponent
import com.dscreate_app.vkclient.di.DaggerApplicationComponent

class NewsFeedApplication: Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}