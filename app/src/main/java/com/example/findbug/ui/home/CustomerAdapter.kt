package com.example.findbug.ui.home

import com.example.findbug.R
import com.example.findbug.base.BaseAdapter
import com.example.findbug.base.BaseDiffCallback
import com.example.findbug.databinding.ItemCustomerListBinding
import com.example.findbug.utils.listener.RVClickListener

// 홈 - 고객 목록 리사이클러뷰 어탭터
class CustomerAdapter(private val clickListener: RVClickListener) : BaseAdapter<CustomerList, ItemCustomerListBinding>(
    BaseDiffCallback (
        itemTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame =  { oldItem, newItem -> oldItem == newItem }
    )
) {
    override val layoutId: Int
        get() = R.layout.item_customer_list

    override fun bind(binding: ItemCustomerListBinding, item: CustomerList) {

        binding.root.setOnClickListener {
            clickListener.onItemClick(item)
        }
    }

}