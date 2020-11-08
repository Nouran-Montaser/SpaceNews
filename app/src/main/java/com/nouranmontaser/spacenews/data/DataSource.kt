package com.nouranmontaser.spacenews.data

import com.nouranmontaser.spacenews.data.model.News
import com.nouranmontaser.spacenews.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DataSource {
    suspend fun getNews(): Flow<Resource<List<News?>>>
}