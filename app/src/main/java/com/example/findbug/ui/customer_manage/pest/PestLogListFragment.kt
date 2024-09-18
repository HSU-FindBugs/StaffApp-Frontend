package com.example.findbug.ui.customer_manage.pest

import android.util.Log
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentPestLogBinding

class PestLogListFragment : BaseFragment<FragmentPestLogBinding>(R.layout.fragment_pest_log_list) {

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        setToolbarNavigation(binding.fragmentPestLogToolbar.toolbarPreviousIb)
    }

}