package com.example.findbug.ui.notification

import com.example.findbug.R
import com.example.findbug.base.BaseAdapter
import com.example.findbug.base.BaseDiffCallback
import com.example.findbug.databinding.ItemNotificationListBinding
import com.example.findbug.domain.model.NotificationItem
import com.example.findbug.utils.listener.RVClickListener

class NotificationRVAdapter(private val clickListener: RVClickListener) : BaseAdapter<NotificationItem, ItemNotificationListBinding>(
    BaseDiffCallback (
        itemTheSame = { oldItem, newItem -> oldItem == newItem },
        contentsTheSame =  { oldItem, newItem -> oldItem == newItem }
    )
) {
    override val layoutId: Int
        get() = R.layout.item_notification_list

    override fun bind(binding: ItemNotificationListBinding, item: NotificationItem) {
        binding.clickListener = clickListener
        binding.notificationItem = item
        binding.root.setOnClickListener {
            clickListener.onItemClick(item)
        }
    }

}