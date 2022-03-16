package dev.theuzfaleiro.network.usecase

import androidx.paging.PagingConfig
import dev.theuzfaleiro.model.DefaultHero
import dev.theuzfaleiro.model.HeroFactory
import dev.theuzfaleiro.network.data.repository.IHeroRepository
import dev.theuzfaleiro.paging.PagingSourceFactory
import dev.theuzfaleiro.testing.rules.CoroutineRule
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetHeroesUseCaseTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutineRule()

    private val heroRepository = mockk<IHeroRepository>()

    private lateinit var getHeroesUseCase: GetHeroesUseCase

    private val defaultHero = HeroFactory().createHero(DefaultHero.Batman)

    private val fakePagingSource = PagingSourceFactory().create(listOf(defaultHero))

    @Before
    fun setUp() {
        getHeroesUseCase = GetHeroesUseCase(heroRepository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`(): Unit =
        runBlocking {
            every {
                heroRepository.getHeroes(any())
            } returns fakePagingSource

            val heroes = getHeroesUseCase(
                params = GetHeroesUseCaseParams(
                    query = "",
                    PagingConfig(pageSize = 20)
                )
            )

            heroes.first() shouldNotBe null
        }

    @After
    fun tearDown() {
    }
}