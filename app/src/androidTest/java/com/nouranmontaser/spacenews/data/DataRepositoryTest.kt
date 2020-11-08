package com.nouranmontaser.spacenews.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.nouranmontaser.spacenews.R
import com.nouranmontaser.spacenews.Utils
import com.nouranmontaser.spacenews.data.local.LocalData
import com.nouranmontaser.spacenews.data.model.News
import com.nouranmontaser.spacenews.data.remote.RemoteData
import com.nouranmontaser.spacenews.utils.Resource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class DataRepositoryTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test-localData")
    lateinit var localData: LocalData

    @Inject
    @Named("test-remoteData")
    lateinit var remoteData: RemoteData

    @Inject
    @Named("test-mockServer")
    lateinit var mockWebServer: MockWebServer


    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getNewsReturnsSuccess(): Unit = runBlocking {
        val mockResponse = MockResponse()
        mockResponse.setResponseCode(200)
        mockResponse.setBody(Utils.readStringFromFile("success_response.json"))
        mockWebServer.enqueue(mockResponse)
        val repository = DataRepository(localData, remoteData)

        val response = repository.getNews().toList()
        mockWebServer.takeRequest()
        Truth.assertThat(response).containsExactly(
            Resource.Loading,
            Resource.Success(
                Utils.readStringFromFile("success_response.json")
            )
        )
    }

    @Test
    fun getNewsReturnsError(): Unit = runBlocking {
        val mockResponse = MockResponse()
        mockResponse.setBody(Utils.readStringFromFile("success_response.json"))
        mockResponse.setResponseCode(404)
        mockWebServer.enqueue(mockResponse)

        val repository = DataRepository(localData, remoteData)

        val response = repository.getNews().toList()
        mockWebServer.takeRequest()
        Truth.assertThat(response).containsExactly(
            Resource.Loading,
            Resource.Error(
                listOf<News>(),
                R.string.no_internet
            )
        )
    }
}