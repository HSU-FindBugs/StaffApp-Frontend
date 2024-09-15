package com.example.findbug.ui.customer_manage.detection

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentDetectionVideoListBinding

class DetectionVideoListFragment : BaseFragment<FragmentDetectionVideoListBinding>(R.layout.fragment_detection_video_list) {

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        Log.d("로그","로그")
    }

    private fun initAdapter() {

    }

}