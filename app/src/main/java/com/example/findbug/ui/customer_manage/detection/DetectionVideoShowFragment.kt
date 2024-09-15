package com.example.findbug.ui.customer_manage.detection

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentDetectionVideoShowBinding

class DetectionVideoShowFragment : BaseFragment<FragmentDetectionVideoShowBinding>(R.layout.fragment_detection_video_show) {

    override fun onPause() {
        super.onPause()
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.BG_Blue200)
    }


    override fun setLayout() {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
    }

    private fun initSettings() {
        Log.d("로그", "로그")
    }

    private fun initAdapter() {

    }

}