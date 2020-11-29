package com.nouranmontaser.spacenews.ui.news.list.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import com.google.common.truth.Truth
import com.nouranmontaser.spacenews.MainCoroutineRule
import com.nouranmontaser.spacenews.R
import com.nouranmontaser.spacenews.data.FakeDataRepository
import com.nouranmontaser.spacenews.data.model.News
import com.nouranmontaser.spacenews.getOrAwaitValueTest
import com.nouranmontaser.spacenews.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
    }


    @Test
    fun `get news success`() {
        val dataRepository = FakeDataRepository()
        dataRepository.shouldReturnNetworkError = false
        val viewModel = NewsViewModel(dataRepository)
        val newsList = viewModel.news.getOrAwaitValueTest()
        Truth.assertThat(newsList).isEqualTo(Resource.Success(
            listOf(
                News("2020-51", "Simulations Show Webb Telescope Can Reveal Distant Galaxies Hidden in Quasars' Glare", "https://webbtelescope.org/contents/news-releases/2020/news-2020-51", "2020-10-14T10:00:00.000-04:00", "")
            )))
    }

    @Test
    fun `get news fails`() {
        val dataRepository = FakeDataRepository()
        dataRepository.shouldReturnNetworkError = true
        val viewModel = NewsViewModel(dataRepository)
        val newsList = viewModel.news.getOrAwaitValueTest()
        Truth.assertThat(newsList).isEqualTo(Resource.Error(null, R.string.no_internet))
    }
}