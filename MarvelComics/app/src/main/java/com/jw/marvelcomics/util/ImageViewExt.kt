package com.jw.marvelcomics.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun ImageView.setImage(imageUrl: String) {
    Glide.with(this.context)
        .load(imageUrl.convertToHttpsUriString())
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}