package com.nouranmontaser.spacenews.data.local

import com.nouranmontaser.spacenews.data.model.News
import javax.inject.Inject

class LocalData @Inject constructor(private val newsDatabase: NewsDatabase): LocalDataSource{

    override suspend fun getSavedNews() = newsDatabase.newsDao().observeAllSavedNews()

    override suspend fun deleteNews() {
        newsDatabase.newsDao().deleteNews()
    }


    override suspend fun saveNews(news: List<News>) {
        newsDatabase.newsDao().insertNews(news)
    }
}