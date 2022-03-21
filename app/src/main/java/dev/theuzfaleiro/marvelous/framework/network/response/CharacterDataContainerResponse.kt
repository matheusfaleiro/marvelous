package dev.theuzfaleiro.marvelous.framework.network.response

import com.google.gson.annotations.SerializedName

data class CharacterDataContainerResponse(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("results")
    val results: List<CharacterResponse>
)