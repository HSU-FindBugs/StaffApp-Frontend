package com.example.findbug.ui.customer_manage.vp

import androidx.navigation.fragment.findNavController
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentCustomerUpsertBinding

class CustomerUpsertFragment :
    BaseFragment<FragmentCustomerUpsertBinding>(R.layout.fragment_customer_upsert) {

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        initButton()
    }

    private fun initButton() {
        binding.fragmentCustomerUpsertSaveBtn.setOnClickListener {
            val action = CustomerHomeFragmentDirections.actionCustomerHomeFragmentToCustomerConfirmFragment()
            findNavController().navigate(action.actionId)
        }
    }

}