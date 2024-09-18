package com.example.findbug.ui.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentNotificationHomeBinding

class NotificationHomeFragment : BaseFragment<FragmentNotificationHomeBinding>(R.layout.fragment_notification_home) {

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        setToolbarNavigation(binding.fragmentCustomerAddCameraToolbar.toolbarPreviousIb)
    }

    private fun initAdapter() {

    }

}