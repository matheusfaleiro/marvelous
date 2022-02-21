package dev.theuzfaleiro.marvelous.framework.network.response

import com.squareup.moshi.Json

data class Character(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail
)
