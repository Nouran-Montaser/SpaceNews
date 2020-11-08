package com.nouranmontaser.spacenews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nouranmontaser.spacenews.data.model.News

@Database(
    entities = [News::class],
    version = 1
)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun newsDao(): NewsDao

}