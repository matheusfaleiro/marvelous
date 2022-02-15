package dev.theuzfaleiro.network.data.network

import dev.theuzfaleiro.network.data.network.response.DataWrapper
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface MarvelService {
    @GET("characters")
    suspend fun getCharacters(@QueryMap queries: Map<String, String>): DataWrapper
}
