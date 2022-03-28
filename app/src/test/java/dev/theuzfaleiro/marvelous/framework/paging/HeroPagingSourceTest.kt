package dev.theuzfaleiro.marvelous.framework.paging

import androidx.paging.PagingSource
import dev.theuzfaleiro.marvelous.framework.network.response.CharacterDataContainerResponse
import dev.theuzfaleiro.marvelous.framework.network.response.CharacterDataWrapperResponse
import dev.theuzfaleiro.marvelous.framework.network.response.CharacterResponse
import dev.theuzfaleiro.marvelous.framework.network.response.ThumbnailResponse
import dev.theuzfaleiro.network.data.repository.IHeroRemoteDataSource
import dev.theuzfaleiro.network.domain.model.Hero
import dev.theuzfaleiro.testing.rules.CoroutineRule
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.mockk.coEvery
import io.mockk.mockk
import java.lang.RuntimeException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HeroPagingSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = CoroutineRule()

    private val heroRemoteDataSource = mockk<IHeroRemoteDataSource<CharacterDataWrapperResponse>>()

    private lateinit var pagingSource: HeroPagingSource

    @Before
    fun setUp() {
        pagingSource = HeroPagingSource(heroRemoteDataSource = heroRemoteDataSource, query = "")
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return a success load result when load is called`() = runTest {
        coEvery {
            heroRemoteDataSource.fetchHeroes(any())
        } returns CharacterDataWrapperResponse(
            copyright = "",
            data = CharacterDataContainerResponse(
                offset = 0,
                total = 2,
                results = listOf(
                    CharacterResponse(
                        id = 0, name = "Spider Man",
                        thumbnail = ThumbnailResponse(
                            path = "",
                            extension = ""
                        )
                    ), CharacterResponse(
                        id = 0, name = "Batman",
                        thumbnail = ThumbnailResponse(
                            path = "",
                            extension = ""
                        )
                    )
                )
            )
        )

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        result shouldBe PagingSource.LoadResult.Page(
            data = listOf(
                Hero(
                    name = "Spider Man",
                    imageUrl = "."
                ),
                Hero(
                    name = "Batman",
                    imageUrl = "."
                )
            ),
            prevKey = null,
            nextKey = 20
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return a error result when load is called`() = runTest {
        coEvery {
            heroRemoteDataSource.fetchHeroes(any())
        } throws RuntimeException()

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        result.shouldBeTypeOf<PagingSource.LoadResult.Error<Any, Any>>()
    }
}