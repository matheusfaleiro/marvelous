package dev.theuzfaleiro.marvelous.framework.network.response

import com.squareup.moshi.Json

data class DataContainer(
    @Json(name = "results")
    val results: List<Character>
)
