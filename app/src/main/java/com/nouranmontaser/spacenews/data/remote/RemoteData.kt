package com.nouranmontaser.spacenews.data.remote

import com.nouranmontaser.spacenews.R
import com.nouranmontaser.spacenews.data.model.News
import com.nouranmontaser.spacenews.utils.Resource
import java.util.ArrayList
import javax.inject.Inject

class RemoteData @Inject constructor(private val apiService: ApiService): RemoteDataSource {

    override suspend fun getNews(): Resource<List<News>> {
        val news = apiService.getNews()
        return if (news.isSuccessful) {
            val newsIdsList = news.body()?.map { it.news_id }
            val newsDetailsList = ArrayList<News>()
            newsIdsList?.forEach {
                val newsDetails = apiService.getNewsDetails(it ?: "").body()
                if (newsDetails != null)
                    newsDetailsList.add(newsDetails)
            }
            Resource.Success(newsDetailsList)
        } else
            Resource.Error(null, R.string.no_internet)
    }
}