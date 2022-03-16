package dev.theuzfaleiro.marvelous.framework.remote

import dev.theuzfaleiro.marvelous.framework.network.MarvelService
import dev.theuzfaleiro.marvelous.framework.network.response.CharacterDataWrapperResponse
import dev.theuzfaleiro.network.data.repository.IHeroRemoteDataSource
import javax.inject.Inject

class HeroRemoteDataSource @Inject constructor(private val marvelService: MarvelService) :
    IHeroRemoteDataSource<CharacterDataWrapperResponse> {

    override suspend fun fetchHeroes(queries: Map<String, String>) =
        marvelService.getCharacters(queries)
}