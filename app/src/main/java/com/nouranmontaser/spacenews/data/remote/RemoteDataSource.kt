package com.nouranmontaser.spacenews.data.remote

import com.nouranmontaser.spacenews.data.model.News
import com.nouranmontaser.spacenews.utils.Resource

interface RemoteDataSource {
    suspend fun getNews(): Resource<List<News>>
}