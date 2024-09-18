package com.example.findbug.ui.customer_manage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentCustomerSearchBinding

class CustomerSearchFragment : BaseFragment<FragmentCustomerSearchBinding>(R.layout.fragment_customer_search) {

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        setToolbarNavigation(binding.fragmentCustomerSearchToolbar.toolbarPreviousIb)
    }

}