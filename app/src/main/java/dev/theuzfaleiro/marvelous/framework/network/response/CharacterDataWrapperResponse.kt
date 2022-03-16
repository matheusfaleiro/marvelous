package dev.theuzfaleiro.marvelous.framework.network.response

import com.squareup.moshi.Json

data class CharacterDataWrapperResponse(
    @Json(name = "copyright")
    val copyright: String,
    @Json(name = "data")
    val data: CharacterDataContainerResponse
)
