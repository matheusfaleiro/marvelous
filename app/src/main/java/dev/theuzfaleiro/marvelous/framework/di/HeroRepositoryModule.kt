package dev.theuzfaleiro.marvelous.framework.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.theuzfaleiro.marvelous.framework.network.response.CharacterDataWrapperResponse
import dev.theuzfaleiro.marvelous.framework.remote.HeroRemoteDataSource
import dev.theuzfaleiro.marvelous.framework.repository.HeroRepository
import dev.theuzfaleiro.network.data.repository.IHeroRemoteDataSource
import dev.theuzfaleiro.network.data.repository.IHeroRepository

@Module
@InstallIn(SingletonComponent::class)
interface HeroRepositoryModule {

    @Binds
    fun bindsHeroRepository(heroRepository: HeroRepository): IHeroRepository

    @Binds
    fun bindsHeroRemoteDataSource(heroRemoteDataSource: HeroRemoteDataSource): IHeroRemoteDataSource<CharacterDataWrapperResponse>
}