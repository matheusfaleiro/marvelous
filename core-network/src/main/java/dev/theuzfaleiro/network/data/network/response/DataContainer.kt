package dev.theuzfaleiro.network.data.network.response

import com.squareup.moshi.Json

data class DataContainer(
    @Json(name = "results")
    val results: List<Character>
)
