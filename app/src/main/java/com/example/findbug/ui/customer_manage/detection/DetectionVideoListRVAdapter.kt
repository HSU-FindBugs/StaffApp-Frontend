package com.example.findbug.ui.customer_manage.detection

import com.example.findbug.R
import com.example.findbug.base.BaseAdapter
import com.example.findbug.base.BaseDiffCallback
import com.example.findbug.databinding.ItemPestListBinding
import com.example.findbug.domain.model.response.DetectionHistory
import com.example.findbug.utils.listener.RVClickListener

// 고객 관리 - 감지 영상 리사이클러뷰 어댑터
class DetectionVideoListRVAdapter(private val clickListener: RVClickListener) : BaseAdapter<DetectionHistory, ItemPestListBinding>(
    BaseDiffCallback (
        itemTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame =  { oldItem, newItem -> oldItem == newItem }
    )
) {
    override val layoutId: Int
        get() = R.layout.item_pest_list

    override fun bind(binding: ItemPestListBinding, item: DetectionHistory) {
        val dateTime = item.date + " " + item.time

        binding.detectionHistory = item
        binding.itemPestListDateTv.text = dateTime
        binding.clickListener = clickListener
        binding.root.setOnClickListener {
            clickListener.onItemClick(item)
        }
    }

}