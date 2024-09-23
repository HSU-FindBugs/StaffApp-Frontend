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
import com.example.findbug.utils.GlobalApplication

class DetectionVideoShowFragment : BaseFragment<FragmentDetectionVideoShowBinding>(R.layout.fragment_detection_video_show) {

    private lateinit var imgUrl: String
    private lateinit var localDateTime: String

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        setToolbarNavigation(binding.fragmentDetectionVideoShowToolbar.toolbarPreviousIb)
        receiveData()
        setData()
    }

    private fun setData() {
        imgUrl.let { GlobalApplication.loadImage(binding.fragmentDetectionVideoShowPreviewIv, imgUrl) }
        binding.fragmentDetectionVideoShowCameraTimeTv.text = localDateTime
    }

    private fun receiveData() {
        arguments.let {
            if (it != null) {
                imgUrl = it.getString("imgUrl") ?: ""
                localDateTime = it.getString("localDateTime") ?: ""
            }
        }
    }

}