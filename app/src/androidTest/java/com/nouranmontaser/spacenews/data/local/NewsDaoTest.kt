package com.nouranmontaser.spacenews.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.nouranmontaser.spacenews.data.model.News
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class NewsDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test-db")
    lateinit var database: NewsDatabase

    private lateinit var dao: NewsDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.newsDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertNews() = runBlockingTest {
        val news = ArrayList<News>()
        news.add(News(
            "2020-51",
            "Simulations Show Webb Telescope Can Reveal Distant Galaxies Hidden in Quasars' Glare",
            "https://webbtelescope.org/contents/news-releases/2020/news-2020-51",
            "2020-10-14T10:00:00.000-04:00",
        ""))
        dao.insertNews(news)

        val allNews = dao.observeAllSavedNews()

        assertThat(allNews).isEqualTo(news)
    }

    @Test
    fun deleteNews() = runBlockingTest {
        val news = ArrayList<News>()
        news.add(News(
            "2020-51",
            "Simulations Show Webb Telescope Can Reveal Distant Galaxies Hidden in Quasars' Glare",
            "https://webbtelescope.org/contents/news-releases/2020/news-2020-51",
            "2020-10-14T10:00:00.000-04:00",
            ""))

        dao.insertNews(news)
        dao.deleteNews()

        val allNews = dao.observeAllSavedNews()

        assertThat(allNews).doesNotContain(news)
    }
}