package com.example.findbug.ui.notification

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.findbug.MainActivity
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentNotificationHomeBinding
import com.example.findbug.domain.model.NotificationItem
import com.example.findbug.ui.home.MainViewModel
import com.example.findbug.utils.listener.RVClickListener
import kotlinx.coroutines.launch

class NotificationHomeFragment : BaseFragment<FragmentNotificationHomeBinding>(R.layout.fragment_notification_home), RVClickListener {

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var notificationRVAdapter: NotificationRVAdapter

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        setToolbarNavigation(binding.fragmentCustomerAddCameraToolbar.toolbarPreviousIb)
        initAdapter()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                val name = if (mainViewModel.name.value.isNullOrEmpty()) "한강진" else mainViewModel.name.value.toString()
                val address = if (mainViewModel.fullAddress.value.isNullOrEmpty()) "한성대학교" else mainViewModel.fullAddress.value.toString()
                val notificationItem = listOf(NotificationItem(name, address))
                notificationRVAdapter.submitList(notificationItem)
            }
        }
    }

    private fun initAdapter() {
        notificationRVAdapter = NotificationRVAdapter(this)
        binding.fragmentNotificationHomeRv.adapter = notificationRVAdapter
    }

    override fun onItemClick(item: Any) { }

}