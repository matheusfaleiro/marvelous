package dev.theuzfaleiro.marvelous.framework.network.response

import com.squareup.moshi.Json
import dev.theuzfaleiro.network.domain.model.Hero

data class CharacterResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "thumbnail")
    val thumbnail: ThumbnailResponse
)

fun CharacterResponse.toHeroModel(): Hero {
    return Hero(
        name = this.name,
        imageUrl = "${this.thumbnail.path}.${this.thumbnail.extension}".replace("http", "https")
    )
}