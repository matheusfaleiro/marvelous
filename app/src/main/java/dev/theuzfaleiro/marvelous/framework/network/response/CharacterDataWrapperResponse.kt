package dev.theuzfaleiro.marvelous.framework.network.response

import com.google.gson.annotations.SerializedName

data class CharacterDataWrapperResponse(
    @SerializedName(value = "copyright")
    val copyright: String,
    @SerializedName(value = "data")
    val data: CharacterDataContainerResponse
)
