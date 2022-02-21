package dev.theuzfaleiro.network.data.repository

import androidx.paging.PagingSource
import dev.theuzfaleiro.network.domain.model.Hero

interface HeroRepository {
    fun getHeroes(): PagingSource<Int, Hero>
}