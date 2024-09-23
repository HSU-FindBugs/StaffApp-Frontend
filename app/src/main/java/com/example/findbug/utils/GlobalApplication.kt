package com.example.findbug.utils

import android.app.Application
import android.widget.ImageView
import com.bumptech.glide.Glide
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GlobalApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: GlobalApplication
            private set

        fun loadImage(imageView: ImageView, source: Any) {
            Glide.with(instance)
                .load(source)
                .into(imageView)
        }
    }

}