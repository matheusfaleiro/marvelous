package dev.theuzfaleiro.marvelous.presentation.heroes.viewmodel

import androidx.paging.PagingData
import dev.theuzfaleiro.model.DefaultHero
import dev.theuzfaleiro.model.HeroFactory
import dev.theuzfaleiro.network.domain.model.Hero
import dev.theuzfaleiro.network.usecase.IGetHeroesUseCase
import dev.theuzfaleiro.testing.rules.CoroutineRule
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.AfterEach

@ExperimentalCoroutinesApi
class HeroViewModelTest {
    @get:Rule
    val coroutineRule = CoroutineRule()

    private val getHeroesUseCase = mockk<IGetHeroesUseCase>()

    private lateinit var heroViewModel: HeroViewModel

    private val heroFactory = HeroFactory()

    @Before
    fun setUp() {
        heroViewModel = HeroViewModel(getHeroesUseCase)
    }

    @Test
    fun `should validate paging object values when fetching heroes`(): Unit =
        runTest(UnconfinedTestDispatcher()) {
            every {
                getHeroesUseCase(any())
            } returns flowOf(
                PagingData.from(
                    listOf(
                        heroFactory.createHero(DefaultHero.SpiderMan),
                        heroFactory.createHero(DefaultHero.Batman),
                        heroFactory.createHero(DefaultHero.Superman),
                    )
                )
            )

            val heroes = heroViewModel.fetchCharacters().first()

            heroes shouldNotBe null
        }


    @Test(expected = RuntimeException::class)
    fun `should thrown an exception when use case returns an error`(): Unit =
        runTest(UnconfinedTestDispatcher()) {
            every {
                getHeroesUseCase(any())
            } throws RuntimeException("Error")

            val heroes = heroViewModel.fetchCharacters().first()

            heroes shouldBe null
        }
}