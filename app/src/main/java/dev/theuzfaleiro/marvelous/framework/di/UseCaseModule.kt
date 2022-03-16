package dev.theuzfaleiro.marvelous.framework.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.theuzfaleiro.network.usecase.GetHeroesUseCase
import dev.theuzfaleiro.network.usecase.IGetHeroesUseCase

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetHeroesUseCase(getHeroesUseCase: GetHeroesUseCase): IGetHeroesUseCase
}