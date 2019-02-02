package com.distribution.christian.cleantest.core.core.util.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


fun ImageView.loadUrl(url: String) {
   Glide.with(context)
         .load(url)
         .apply(RequestOptions.circleCropTransform())
         .into(this)
}

fun ImageView.loadResource(id: Int) {
   Glide.with(context)
         .load(id)
         .apply(RequestOptions.circleCropTransform())
         .into(this)
}