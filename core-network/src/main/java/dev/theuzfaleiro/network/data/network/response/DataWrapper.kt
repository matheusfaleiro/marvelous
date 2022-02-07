package dev.theuzfaleiro.network.data.network.response

import com.squareup.moshi.Json

data class DataWrapper(
    @Json(name = "copyright")
    val copyright: String,
    @Json(name = "data")
    val data: DataContainer
)
