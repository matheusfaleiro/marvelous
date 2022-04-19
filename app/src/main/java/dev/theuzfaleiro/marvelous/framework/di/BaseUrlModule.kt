package dev.theuzfaleiro.marvelous.framework.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.theuzfaleiro.marvelous.BuildConfig
import dev.theuzfaleiro.marvelous.framework.di.qualifier.BaseUrl

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlModule {

    @BaseUrl
    @Provides
    fun providesBaseUrl() = BuildConfig.BASE_URL
}
