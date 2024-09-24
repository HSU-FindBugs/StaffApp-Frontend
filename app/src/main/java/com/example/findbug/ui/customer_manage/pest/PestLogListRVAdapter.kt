package com.example.findbug.ui.customer_manage.pest

import android.os.Build
import com.example.findbug.R
import com.example.findbug.base.BaseAdapter
import com.example.findbug.base.BaseDiffCallback
import com.example.findbug.databinding.ItemPestListBinding
import com.example.findbug.domain.model.response.DetectionHistory
import com.example.findbug.utils.listener.RVClickListener
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// 고객 관리 - 해충기록 리사이클러뷰 어댑터
class PestLogListRVAdapter(private val clickListener: RVClickListener) : BaseAdapter<DetectionHistory, ItemPestListBinding>(
    BaseDiffCallback (
        itemTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame =  { oldItem, newItem -> oldItem == newItem }
    )
) {
    override val layoutId: Int
        get() = R.layout.item_pest_list

    override fun bind(binding: ItemPestListBinding, item: DetectionHistory) {

        val inputFormatter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        val dateTime = LocalDateTime.parse(item.localDateTime, inputFormatter)
        val outputFormatter = DateTimeFormatter.ofPattern("yyyy MM.dd HH:mm")
        val formattedDateTime = dateTime.format(outputFormatter)

        binding.detectionHistory = item
        binding.itemPestListDateTv.text = formattedDateTime
        binding.clickListener = clickListener
        binding.root.setOnClickListener {
            clickListener.onItemClick(item)
        }
    }

}