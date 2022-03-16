package dev.theuzfaleiro.marvelous.framework.network.response

import com.squareup.moshi.Json

data class CharacterDataContainerResponse(
    @Json(name = "offset")
    val offset: Int,
    @Json(name = "total")
    val total: Int,
    @Json(name = "results")
    val results: List<CharacterResponse>
)