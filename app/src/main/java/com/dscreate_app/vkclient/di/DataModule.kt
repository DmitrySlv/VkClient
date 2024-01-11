package com.dscreate_app.vkclient.di

import android.content.Context
import com.dscreate_app.vkclient.data.network.ApiFactory
import com.dscreate_app.vkclient.data.network.ApiService
import com.dscreate_app.vkclient.data.repository.NewsFeedRepositoryImpl
import com.dscreate_app.vkclient.domain.repository.NewsFeedRepository
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: NewsFeedRepositoryImpl): NewsFeedRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @ApplicationScope
        @Provides
        fun provideVkStorage(
            context: Context
        ): VKPreferencesKeyValueStorage {
            return VKPreferencesKeyValueStorage(context)
        }
    }
}