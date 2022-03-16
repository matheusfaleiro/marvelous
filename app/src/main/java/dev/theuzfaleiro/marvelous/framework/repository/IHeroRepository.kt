package dev.theuzfaleiro.marvelous.framework.repository

import androidx.paging.PagingSource
import dev.theuzfaleiro.marvelous.framework.network.response.CharacterDataWrapperResponse
import dev.theuzfaleiro.marvelous.framework.paging.HeroPagingSource
import dev.theuzfaleiro.network.data.repository.IHeroRemoteDataSource
import dev.theuzfaleiro.network.data.repository.IHeroRepository
import dev.theuzfaleiro.network.domain.model.Hero
import javax.inject.Inject

class HeroRepository @Inject constructor(private val remoteDataSource: IHeroRemoteDataSource<CharacterDataWrapperResponse>) :
    IHeroRepository {

    override fun getHeroes(query: String): PagingSource<Int, Hero> =
        HeroPagingSource(heroRemoteDataSource = remoteDataSource, query = query)
}