package com.nouranmontaser.spacenews.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "news")
@Parcelize
data class News (

    @PrimaryKey
    @Json(name = "news_id")
    val news_id: String,
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "publication")
    val publication: String?,
    @Json(name = "thumbnail")
    val thumbnail: String?
) : Parcelable