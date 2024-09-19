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
import com.example.findbug.databinding.FragmentCustomerAddCameraBinding

class CustomerAddCameraFragment : BaseFragment<FragmentCustomerAddCameraBinding>(R.layout.fragment_customer_add_camera) {

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        setToolbarNavigation(binding.fragmentCustomerAddCameraToolbar.toolbarPreviousIb)
    }

    private fun initEditText() {

    }

}