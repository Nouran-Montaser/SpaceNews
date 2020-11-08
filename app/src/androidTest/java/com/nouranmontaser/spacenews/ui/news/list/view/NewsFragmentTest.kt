package com.nouranmontaser.spacenews.ui.news.list.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import com.nouranmontaser.spacenews.R
import com.nouranmontaser.spacenews.data.model.News
import com.nouranmontaser.spacenews.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class NewsFragmentTest  {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun clickNewsItem_navigateToNewsDetailsFragment() {
        val navController = mock(NavController::class.java) // like object of class navController but without function implementation
        launchFragmentInHiltContainer<NewsFragment> {
            Navigation.setViewNavController(requireView(), navController)
            adapter.newsList = listOf(
                News("2020-51", "Simulations Show Webb Telescope Can Reveal Distant Galaxies Hidden in Quasars' Glare", "https://webbtelescope.org/contents/news-releases/2020/news-2020-51", "2020-10-14T10:00:00.000-04:00", "james_webb", "The brightest objects in the distant, young universe are quasars. These cosmic beacons are powered by supermassive black holes consuming material at a ferocious rate. Quasars are so bright that they can outshine their entire host galaxy, making it difficult to study those galaxies and compare them to galaxies without quasars.\r\n\r\nA new theoretical study examines how well NASA’s upcoming James Webb Space Telescope, slated for launch in 2021, will be able to separate the light of host galaxies from the bright central quasar. The researchers find that Webb could detect host galaxies that existed just 1 billion years after the big bang.", "", "", "", "", "", "")
            )
        }

        onView(withId(R.id.news_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        verify(navController).navigate(NewsFragmentDirections.actionNewsFragmentToNewsDetailsFragment())
    }

    @Test
    fun displayNewsListSucceeded() {
        launchFragmentInHiltContainer<NewsFragment> {
            adapter.newsList = listOf(
                News("2020-51", "Simulations Show Webb Telescope Can Reveal Distant Galaxies Hidden in Quasars' Glare", "https://webbtelescope.org/contents/news-releases/2020/news-2020-51", "2020-10-14T10:00:00.000-04:00", "james_webb", "The brightest objects in the distant, young universe are quasars. These cosmic beacons are powered by supermassive black holes consuming material at a ferocious rate. Quasars are so bright that they can outshine their entire host galaxy, making it difficult to study those galaxies and compare them to galaxies without quasars.\r\n\r\nA new theoretical study examines how well NASA’s upcoming James Webb Space Telescope, slated for launch in 2021, will be able to separate the light of host galaxies from the bright central quasar. The researchers find that Webb could detect host galaxies that existed just 1 billion years after the big bang.", "", "", "", "", "", "")
            )
        }
        onView(withId(R.id.news_recycler_view)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }


    @Test
    fun displayNewsListFailed() {
        launchFragmentInHiltContainer<NewsFragment> {
            adapter.newsList = emptyList()
        }
        onView(withId(R.id.no_news_tv)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }
}