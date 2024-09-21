package com.example.findbug.utils.extension

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.findbug.domain.model.request.Address

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .apply(RequestOptions().centerCrop())
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("getAddress")
    fun getAddress(view: TextView, address: Address?) {
        // 주소를 하나의 문자열로 합침
        val formattedAddress = buildString {
            address?.let {
                if (!it.region_1depth.isNullOrEmpty()) append(it.region_1depth)
                if (!it.region_2depth.isNullOrEmpty()) append(" ${it.region_2depth}")
                if (!it.region_3depth.isNullOrEmpty()) append(" ${it.region_3depth}")
                if (!it.street_name.isNullOrEmpty()) append(" ${it.street_name}")
                if (!it.detail_address.isNullOrEmpty()) append(" ${it.detail_address}")
            }
        }.trim() // 앞뒤 공백 제거

        view.text = formattedAddress // TextView에 설정
    }

}