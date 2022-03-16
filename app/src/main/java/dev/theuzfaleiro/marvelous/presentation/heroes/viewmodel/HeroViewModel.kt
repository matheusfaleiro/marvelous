package dev.theuzfaleiro.marvelous.presentation.heroes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.theuzfaleiro.network.domain.model.Hero
import dev.theuzfaleiro.network.usecase.GetHeroesUseCase
import dev.theuzfaleiro.network.usecase.GetHeroesUseCaseParams
import dev.theuzfaleiro.network.usecase.IGetHeroesUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

private const val MAX_PAGE_SIZE = 20

@HiltViewModel
class HeroViewModel @Inject constructor(private val getHeroesUseCase: IGetHeroesUseCase) :
    ViewModel() {

    fun fetchCharacters(query: String = ""): Flow<PagingData<Hero>> {
        return getHeroesUseCase(
            GetHeroesUseCaseParams(query, getPagingConfig())
        ).cachedIn(viewModelScope)
    }

    private fun getPagingConfig() = PagingConfig(pageSize = MAX_PAGE_SIZE)
}