package dev.theuzfaleiro.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.theuzfaleiro.network.domain.model.Hero

class PagingSourceFactory {

    fun create(heroes : List<Hero>) = object : PagingSource<Int, Hero>() {
        override fun getRefreshKey(state: PagingState<Int, Hero>) = 1

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
            return LoadResult.Page(
                data = heroes,
                prevKey = null,
                nextKey = 20
            )
        }

    }
}