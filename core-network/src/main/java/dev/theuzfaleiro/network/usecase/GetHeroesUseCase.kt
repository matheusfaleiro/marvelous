package dev.theuzfaleiro.network.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.theuzfaleiro.network.data.repository.IHeroRepository
import dev.theuzfaleiro.network.domain.model.Hero
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

data class GetHeroesUseCaseParams(val query: String, val pagingConfig: PagingConfig)

interface IGetHeroesUseCase {
    operator fun invoke(params: GetHeroesUseCaseParams): Flow<PagingData<Hero>>
}

class GetHeroesUseCase @Inject constructor(private val heroRepository: IHeroRepository) :
    PagingUseCase<GetHeroesUseCaseParams, Hero>(), IGetHeroesUseCase {

    override fun doWork(params: GetHeroesUseCaseParams): Flow<PagingData<Hero>> {
        return Pager(config = params.pagingConfig) {
            heroRepository.getHeroes(params.query)
        }.flow
    }
}

