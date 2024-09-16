package com.example.findbug.ui.customer_manage.vp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentCustomerFindBinding
import com.example.findbug.utils.extension.navigateSafe

class CustomerFindFragment : BaseFragment<FragmentCustomerFindBinding>(R.layout.fragment_customer_find) {

    override fun setLayout() {
        goSearchPage()
    }

    private fun initSettings() {
        goSearchPage()
    }

    private fun goSearchPage() {

        val action = CustomerHomeFragmentDirections.actionCustomerHomeFragmentToCustomerSearchFragment()
        with(binding) {
            fragmentCustomerFindSearchTv.setOnClickListener {
                findNavController().navigateSafe(action.actionId)
            }
            fragmentCustomerFindSearchIb.setOnClickListener {
                findNavController().navigateSafe(action.actionId)
            }
        }
    }

}