package com.example.findbug.ui.customer_manage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentCustomerReportBinding

class CustomerReportFragment : BaseFragment<FragmentCustomerReportBinding>(R.layout.fragment_customer_report) {

    override fun onPause() {
        super.onPause()
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.BG_Blue200)
    }

    override fun setLayout() {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)
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