package dev.theuzfaleiro.marvelous.framework.network

import dev.theuzfaleiro.marvelous.framework.network.response.DataWrapper
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelService {
    @GET("characters")
    suspend fun getCharacters(@QueryMap queries: Map<String, String>): DataWrapper
}
