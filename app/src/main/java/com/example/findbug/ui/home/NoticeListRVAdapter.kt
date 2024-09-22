package com.example.findbug.ui.home

import com.example.findbug.R
import com.example.findbug.base.BaseAdapter
import com.example.findbug.base.BaseDiffCallback
import com.example.findbug.databinding.ItemNoticeListBinding
import com.example.findbug.domain.model.response.NotificationDto
import com.example.findbug.utils.listener.RVClickListener

// 홈 - 공지사항 리사이클러뷰 어댑터
class NoticeListRVAdapter(private val clickListener: RVClickListener) : BaseAdapter<NotificationDto, ItemNoticeListBinding>(
    BaseDiffCallback (
        itemTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame =  { oldItem, newItem -> oldItem == newItem }
    )
) {
    override val layoutId: Int
        get() = R.layout.item_notice_list

    override fun bind(binding: ItemNoticeListBinding, item: NotificationDto) {
        binding.notificationDto = item
        binding.clickListener = clickListener

        binding.root.setOnClickListener {
            clickListener.onItemClick(item)
        }
    }

}