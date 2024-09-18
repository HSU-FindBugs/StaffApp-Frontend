package com.example.findbug.ui.home

import com.example.findbug.R
import com.example.findbug.base.BaseAdapter
import com.example.findbug.base.BaseDiffCallback
import com.example.findbug.databinding.ItemCustomerListBinding
import com.example.findbug.utils.listener.RVClickListener

// 홈 - 공지사항 리사이클러뷰 어탭터
class NoticeListAdapter(private val clickListener: RVClickListener) : BaseAdapter<DummyTest, ItemCustomerListBinding>(
    BaseDiffCallback (
        itemTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame =  { oldItem, newItem -> oldItem == newItem }
    )
) {
    override val layoutId: Int
        get() = R.layout.item_customer_list

    override fun bind(binding: ItemCustomerListBinding, item: DummyTest) {

        binding.root.setOnClickListener {
            clickListener.onItemClick(item)
        }
    }

}