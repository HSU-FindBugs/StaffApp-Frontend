package com.example.findbug.ui.customer_manage.vp

import com.example.findbug.R
import com.example.findbug.base.BaseAdapter
import com.example.findbug.base.BaseDiffCallback
import com.example.findbug.databinding.ItemCustomerListBinding
import com.example.findbug.domain.model.response.ManagementPageMemberDto
import com.example.findbug.utils.listener.RVClickListener
import java.text.SimpleDateFormat
import java.util.Locale

// 고객 관리 - 홈 (탭1) - 고객 목록 리사이클러뷰 어댑터
class CustomerListRVAdapter(private val clickListener: RVClickListener): BaseAdapter<ManagementPageMemberDto, ItemCustomerListBinding>(
    diffCallback = BaseDiffCallback(
        itemTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame =  { oldItem, newItem -> oldItem == newItem }
    )
) {
    override val layoutId: Int
        get() = R.layout.item_customer_list

    override fun bind(binding: ItemCustomerListBinding, item: ManagementPageMemberDto) {

        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("yyyy. MM. dd", Locale.getDefault())
        val date = item.recentVisit.let { inputFormat.parse(it) }
        val formattedDate = date?.let { outputFormat.format(it) }

        binding.pageMember = item
        binding.itemCustomerListTimeTv.text = "최근 방문: ${formattedDate ?: "날짜 정보 없음"}"
        binding.clickListener = clickListener

        binding.root.setOnClickListener {
            clickListener.onItemClick(item)
        }
    }

}