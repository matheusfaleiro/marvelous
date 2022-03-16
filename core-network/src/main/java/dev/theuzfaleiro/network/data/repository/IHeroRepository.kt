package dev.theuzfaleiro.network.data.repository

import androidx.paging.PagingSource
import dev.theuzfaleiro.network.domain.model.Hero

interface IHeroRepository {
    fun getHeroes(query: String): PagingSource<Int, Hero>
}