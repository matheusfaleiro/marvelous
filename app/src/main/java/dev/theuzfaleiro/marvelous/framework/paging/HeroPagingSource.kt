package dev.theuzfaleiro.marvelous.framework.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.theuzfaleiro.marvelous.framework.network.response.CharacterDataWrapperResponse
import dev.theuzfaleiro.marvelous.framework.network.response.toHeroModel
import dev.theuzfaleiro.network.data.repository.IHeroRemoteDataSource
import dev.theuzfaleiro.network.domain.model.Hero
import javax.inject.Inject

private const val INITIAL_OFFSET = 0
private const val OFFSET_LIMIT = 20

class HeroPagingSource @Inject constructor(
    private val heroRemoteDataSource: IHeroRemoteDataSource<CharacterDataWrapperResponse>,
    private val query: String
) : PagingSource<Int, Hero>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        return try {
            val offset = params.key ?: INITIAL_OFFSET

            val queries = hashMapOf(
                "offset" to offset.toString()
            )

            if (query.isNotEmpty()) {
                queries["nameStartsWith"] = query
            }

            val response = heroRemoteDataSource.fetchHeroes(queries)

            val responseOffset = response.data.offset
            val totalCharacters = response.data.total

            LoadResult.Page(
                data = response.data.results.map { it.toHeroModel() },
                prevKey = null,
                nextKey = if (responseOffset < totalCharacters) {
                    responseOffset + OFFSET_LIMIT
                } else {
                    null
                }
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)

            anchorPage?.prevKey?.plus(other = OFFSET_LIMIT)
                ?: anchorPage?.nextKey?.minus(other = OFFSET_LIMIT)
        }
    }
}