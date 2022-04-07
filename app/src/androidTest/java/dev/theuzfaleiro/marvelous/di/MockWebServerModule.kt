package dev.theuzfaleiro.marvelous.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.theuzfaleiro.marvelous.framework.di.qualifier.BaseUrl

@Module
@InstallIn(SingletonComponent::class)
object MockWebServerModule {

    @BaseUrl
    @Provides
    fun providesBaseUrl() = "http://localhost:8080/"
}
