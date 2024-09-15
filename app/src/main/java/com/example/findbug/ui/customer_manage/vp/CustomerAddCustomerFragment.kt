package com.example.findbug.ui.customer_manage.vp

import androidx.navigation.fragment.findNavController
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentAddCustomerBinding

class CustomerAddCustomerFragment :
    BaseFragment<FragmentAddCustomerBinding>(R.layout.fragment_add_customer) {

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