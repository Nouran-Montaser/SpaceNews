package com.nouranmontaser.spacenews.data.local

import com.nouranmontaser.spacenews.data.model.News

interface LocalDataSource {
    suspend fun getSavedNews(): List<News>
    suspend fun deleteNews()
    suspend fun saveNews(news: List<News>)
}