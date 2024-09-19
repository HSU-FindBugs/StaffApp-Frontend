package com.example.findbug.ui.customer_manage

import android.util.Log
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentCustomerReportEditBinding

class CustomerReportEditFragment : BaseFragment<FragmentCustomerReportEditBinding>(R.layout.fragment_customer_report_edit) {

    override fun onPause() {
        super.onPause()
    }

    override fun setLayout() {
        setToolbarNavigation(binding.fragmentCustomerReportToolbar.toolbarPreviousIb)
        initSettings()
    }

    private fun initSettings() {
        initButton()
    }

    private fun initButton() {
        binding.fragmentCustomerReportSaveBtn.setOnClickListener {
            Log.d("로그","로그")
        }
    }

}