package dev.theuzfaleiro.marvelous.presentation.heroes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dev.theuzfaleiro.marvelous.R
import dev.theuzfaleiro.marvelous.framework.di.BaseUrlModule
import dev.theuzfaleiro.marvelous.presentation.heroes.adapter.HeroesViewHolder
import dev.theuzfaleiro.marvelous.util.asJsonString
import dev.theuzfaleiro.marvelous.util.launchFragmentInHiltContainer
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val DEFAULT_PORT = 8080
private const val FIRST_HERO_PAGE = "first_page_heroes.json"
private const val SECOND_HERO_PAGE = "second_page_heroes.json"

@UninstallModules(BaseUrlModule::class)
@HiltAndroidTest
class HeroesFragmentTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer().apply { start(port = DEFAULT_PORT) }

        launchFragmentInHiltContainer<HeroesFragment>()
    }

    @Test
    fun shouldShowHeroes_WhenViewIsCreated() {
        mockWebServer.enqueue(MockResponse().setBody(FIRST_HERO_PAGE.asJsonString()))

        onView(withId(R.id.recyclerViewHeroes))
            .check(matches(isDisplayed()))
    }

    @Test
    fun shouldLoadNextHeroesPage_WhenScrollToTheBottom() {
        with(mockWebServer) {
            enqueue(MockResponse().setBody(FIRST_HERO_PAGE.asJsonString()))
            enqueue(MockResponse().setBody(SECOND_HERO_PAGE.asJsonString()))
        }

        onView(withId(R.id.recyclerViewHeroes))
            .perform(scrollToPosition<HeroesViewHolder>(20))

        onView(withText("Amora"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun shouldShowErrorMessage_WhenAnErrorHappenedLoadingHeroesContent() {
        with(mockWebServer) {
            enqueue(MockResponse().setResponseCode(400))
        }

        onView(withId(R.id.includeLoadingError))
            .check(matches(isDisplayed()))
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}