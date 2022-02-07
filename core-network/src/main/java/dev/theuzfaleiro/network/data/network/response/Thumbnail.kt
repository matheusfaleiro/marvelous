package dev.theuzfaleiro.network.data.network.response

import com.squareup.moshi.Json

data class Thumbnail(
    @Json(name = "path")
    val path: String,
    @Json(name = "extension")
    val extension: String
)
