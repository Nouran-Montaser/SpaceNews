package com.nouranmontaser.spacenews.data

import com.nouranmontaser.spacenews.R
import com.nouranmontaser.spacenews.data.model.News
import com.nouranmontaser.spacenews.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeDataRepository: DataSource {

    var shouldReturnNetworkError = false

    override suspend fun getNews(): Flow<Resource<List<News?>>> {
        return flow {
            if (shouldReturnNetworkError) {
                emit(Resource.Error(null, R.string.no_internet))
            } else {
                emit(Resource.Success(
                    listOf(News("2020-51", "Simulations Show Webb Telescope Can Reveal Distant Galaxies Hidden in Quasars' Glare", "https://webbtelescope.org/contents/news-releases/2020/news-2020-51", "2020-10-14T10:00:00.000-04:00", "")
                )))
            }
        }
    }

}