package com.khodkari.movietoemoji.data.di.module

import com.khodkari.movietoemoji.BuildConfig
import com.khodkari.movietoemoji.data.api.Api
import com.khodkari.movietoemoji.data.api.OpenAIApi
import com.khodkari.movietoemoji.data.di.qualifier.ApiKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @ApiKey
    @Singleton
    @Provides
    fun provideApiKey() = BuildConfig.API_KEY

    @Singleton
    @Provides
    fun provideOpenAIApi(@ApiKey apiKey: String): Api = OpenAIApi(apiKey)
}