package dev.theuzfaleiro.network.data.repository

interface HeroRemoteDataSource<T> {
    suspend fun fetchHeroes(queries: Map<String, String>): T
}