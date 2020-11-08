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
                    listOf(News("2020-51", "Simulations Show Webb Telescope Can Reveal Distant Galaxies Hidden in Quasars' Glare", "https://webbtelescope.org/contents/news-releases/2020/news-2020-51", "2020-10-14T10:00:00.000-04:00", "james_webb", "The brightest objects in the distant, young universe are quasars. These cosmic beacons are powered by supermassive black holes consuming material at a ferocious rate. Quasars are so bright that they can outshine their entire host galaxy, making it difficult to study those galaxies and compare them to galaxies without quasars.\r\n\r\nA new theoretical study examines how well NASA’s upcoming James Webb Space Telescope, slated for launch in 2021, will be able to separate the light of host galaxies from the bright central quasar. The researchers find that Webb could detect host galaxies that existed just 1 billion years after the big bang.", "", "", "", "", "", "")
                )))
            }
        }
    }

}