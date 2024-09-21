package com.example.findbug.utils.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .apply(RequestOptions().centerCrop())
            .into(view)
    }

}