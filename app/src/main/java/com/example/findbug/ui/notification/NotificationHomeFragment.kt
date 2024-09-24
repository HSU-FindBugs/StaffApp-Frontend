package com.example.findbug.ui.notification

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.findbug.MainActivity
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentNotificationHomeBinding

class NotificationHomeFragment : BaseFragment<FragmentNotificationHomeBinding>(R.layout.fragment_notification_home) {

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        setToolbarNavigation(binding.fragmentCustomerAddCameraToolbar.toolbarPreviousIb)
        val bugDetectionAlertResponse = (activity as? MainActivity)?.getSseEventData()
        bugDetectionAlertResponse?.let {
            // 데이터를 처리
            Log.d("SSE Alert", "Name: ${it.name}, Address: ${it.address}, Time: ${it.recentFindTime}")
        } ?: run { Log.d("SSE Alert", "No data available")
        }

    }

    private fun initAdapter() {

    }

}