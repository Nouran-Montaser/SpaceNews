package com.nouranmontaser.spacenews.data

import com.nouranmontaser.spacenews.R
import com.nouranmontaser.spacenews.data.local.LocalData
import com.nouranmontaser.spacenews.data.model.News
import com.nouranmontaser.spacenews.utils.Resource
import com.nouranmontaser.spacenews.data.remote.RemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DataRepository @Inject constructor(
    private val localData: LocalData,
    private val remoteData: RemoteData
): DataSource {

    override suspend fun getNews(): Flow<Resource<List<News?>>> {
        return flow {
            emit(Resource.Loading)
            val news: List<News?>? = localData.getSavedNews()
            try {
                val newsList = remoteData.getNews()
                if (newsList is Resource.Success) {
                   newsList.data?.let{newsItem->
                        localData.deleteNews()
                        localData.saveNews(newsItem)
                        emit(Resource.Success(newsItem))
                    }
                } else
                    emit(Resource.Error(news, R.string.no_internet))
            } catch (e: Exception) {
                emit(Resource.Error(news,  R.string.no_internet))
            }
        }
    }

}