package dev.theuzfaleiro.marvelous.framework.network.response

import com.google.gson.annotations.SerializedName
import dev.theuzfaleiro.network.domain.model.Hero

data class CharacterResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
)

fun CharacterResponse.toHeroModel(): Hero {
    return Hero(
        name = this.name,
        imageUrl = "${this.thumbnail.path}.${this.thumbnail.extension}".replace("http", "https")
    )
}