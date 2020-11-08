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
    @Json(name = "mission")
    val mission: String?,
    @Json(name = "abstract")
    val abstractNews: String?,
    @Json(name = "thumbnail")
    val thumbnail: String?,
    @Json(name = "thumbnail_retina")
    val thumbnail_retina: String?,
    @Json(name = "thumbnail_1x")
    val thumbnail_1x: String?,
    @Json(name = "thumbnail_2x")
    val thumbnail_2x: String?,
    @Json(name = "keystone_image_1x")
    val keystone_image_1x: String?,
    @Json(name = "keystone_image_2x")
    val keystone_image_2x: String?,
    val isFavourite: Boolean? = false,
) : Parcelable