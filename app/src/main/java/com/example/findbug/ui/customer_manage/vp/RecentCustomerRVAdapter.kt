package com.example.findbug.ui.customer_manage.vp

import com.example.findbug.R
import com.example.findbug.base.BaseAdapter
import com.example.findbug.base.BaseDiffCallback
import com.example.findbug.databinding.ItemCustomerListBinding
import com.example.findbug.databinding.ItemRecentSearchBinding
import com.example.findbug.ui.home.DummyTest
import com.example.findbug.utils.listener.RVClickListener

// 고객 관리(검색 화면) - 최근 검색한 고객 리사이클러뷰 어댑터
class RecentCustomerRVAdapter(private val clickListener: RVClickListener): BaseAdapter<DummyTest, ItemRecentSearchBinding>(
    diffCallback = BaseDiffCallback(
        itemTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame =  { oldItem, newItem -> oldItem == newItem }
    )
) {
    override val layoutId: Int
        get() = R.layout.item_recent_search

    override fun bind(binding: ItemRecentSearchBinding, item: DummyTest) {

        binding.clickListener = clickListener
        binding.root.setOnClickListener {
            clickListener.onItemClick(item)
        }
    }

}