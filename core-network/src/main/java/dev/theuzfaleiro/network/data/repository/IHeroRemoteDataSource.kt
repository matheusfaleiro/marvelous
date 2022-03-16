package dev.theuzfaleiro.network.data.repository

interface IHeroRemoteDataSource<T> {
    suspend fun fetchHeroes(queries: Map<String, String>): T
}