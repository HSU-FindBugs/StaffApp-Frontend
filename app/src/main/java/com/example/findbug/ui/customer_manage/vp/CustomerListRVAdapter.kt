package com.example.findbug.ui.customer_manage.vp

import com.example.findbug.R
import com.example.findbug.base.BaseAdapter
import com.example.findbug.base.BaseDiffCallback
import com.example.findbug.databinding.ItemCustomerListBinding
import com.example.findbug.ui.home.DummyTest
import com.example.findbug.utils.listener.RVClickListener

// 고객 관리 - 홈 (탭1) - 고객 목록 리사이클러뷰 어댑터
class CustomerListRVAdapter(private val clickListener: RVClickListener): BaseAdapter<DummyTest, ItemCustomerListBinding>(
    diffCallback = BaseDiffCallback(
        itemTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame =  { oldItem, newItem -> oldItem == newItem }
    )
) {
    override val layoutId: Int
        get() = R.layout.item_customer_list

    override fun bind(binding: ItemCustomerListBinding, item: DummyTest) {

        binding.clickListener = clickListener
        binding.root.setOnClickListener {
            clickListener.onItemClick(item)
        }
    }

}