package com.nouranmontaser.spacenews.data.remote

import com.nouranmontaser.spacenews.data.model.News
import com.nouranmontaser.spacenews.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/v3/news")
    suspend fun getNews(): Response<List<NewsResponse>>

    @GET("/api/v3/news_release/{id}")
    suspend fun getNewsDetails(@Path("id") id: String): Response<News>
}