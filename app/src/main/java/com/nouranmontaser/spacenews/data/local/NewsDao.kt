package com.nouranmontaser.spacenews.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nouranmontaser.spacenews.data.model.News

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<News>)

    @Query("DELETE FROM news")
    suspend fun deleteNews()

    @Query("Select * from news")
    suspend fun observeAllSavedNews(): List<News>
}