package com.example.findbug.ui.customer_manage.detection

import com.example.findbug.R
import com.example.findbug.base.BaseAdapter
import com.example.findbug.base.BaseDiffCallback
import com.example.findbug.databinding.ItemPestListBinding
import com.example.findbug.ui.home.DummyTest
import com.example.findbug.utils.listener.RVClickListener

// 고객 관리 - 감지 영상 리사이클러뷰 어댑터
class PestLogListRVAdapter(private val clickListener: RVClickListener) : BaseAdapter<DummyTest, ItemPestListBinding>(
    BaseDiffCallback (
        itemTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame =  { oldItem, newItem -> oldItem == newItem }
    )
) {
    override val layoutId: Int
        get() = R.layout.fragment_pest_log_list

    override fun bind(binding: ItemPestListBinding, item: DummyTest) {
        binding.clickListener = clickListener
        binding.root.setOnClickListener {
            clickListener.onItemClick(item)
        }
    }

}