package com.nouranmontaser.spacenews.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsResponse (
    @Json(name = "news_id")
    val news_id: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
) : Parcelable