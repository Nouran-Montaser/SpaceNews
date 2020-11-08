package com.nouranmontaser.spacenews.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.nouranmontaser.spacenews.R
import com.nouranmontaser.spacenews.di.module.ApplicationModule

@BindingAdapter("imageUrl")
fun ImageView.load(link: String) {
    Glide.with(this.context).applyDefaultRequestOptions(
        RequestOptions().placeholder(R.drawable.progress_animation).dontAnimate()
    ).load("https:$link").into(this)
}