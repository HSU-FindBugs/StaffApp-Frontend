package com.example.findbug.ui.customer_manage

import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentLiveStreamBinding

class LiveStreamFragment : BaseFragment<FragmentLiveStreamBinding>(R.layout.fragment_live_stream) {

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        setToolbarNavigation(binding.fragmentLiveStreamToolbar.toolbarPreviousIb)
        initButtons()
    }

    private fun initButtons() {

    }

}