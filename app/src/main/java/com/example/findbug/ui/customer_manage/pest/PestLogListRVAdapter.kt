package com.example.findbug.ui.customer_manage.pest

import com.example.findbug.R
import com.example.findbug.base.BaseAdapter
import com.example.findbug.base.BaseDiffCallback
import com.example.findbug.databinding.ItemPestListBinding
import com.example.findbug.domain.model.response.DetectionHistory
import com.example.findbug.ui.home.DummyTest
import com.example.findbug.utils.listener.RVClickListener

// 고객 관리 - 해충기록 리사이클러뷰 어댑터
class PestLogListRVAdapter(private val clickListener: RVClickListener) : BaseAdapter<DetectionHistory, ItemPestListBinding>(
    BaseDiffCallback (
        itemTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame =  { oldItem, newItem -> oldItem == newItem }
    )
) {
    override val layoutId: Int
        get() = R.layout.fragment_pest_log_list

    override fun bind(binding: ItemPestListBinding, item: DetectionHistory) {
        binding.detectionHistory = item
        binding.clickListener = clickListener
        binding.root.setOnClickListener {
            clickListener.onItemClick(item)
        }
    }

}