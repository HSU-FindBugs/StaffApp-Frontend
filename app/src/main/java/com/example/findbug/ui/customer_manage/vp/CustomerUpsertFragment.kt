package com.example.findbug.ui.customer_manage.vp

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentCustomerHomeBinding
import com.example.findbug.databinding.FragmentCustomerUpsertBinding
import com.example.findbug.ui.customer_manage.CustomerHomeFragmentDirections
import com.example.findbug.utils.OptionSpinnerAdapter

class CustomerUpsertFragment :
    BaseFragment<FragmentCustomerUpsertBinding>(R.layout.fragment_customer_upsert) {

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        initSpinner()
        initButton()
    }

    private fun initButton() {
        binding.fragmentCustomerUpsertSaveBtn.setOnClickListener {
            val action = CustomerHomeFragmentDirections.actionCustomerHomeFragmentToCustomerConfirmFragment()
            findNavController().navigate(action.actionId)
        }
    }

    private fun initSpinner() {
        val spinnerList = resources.getStringArray(R.array.spinnerList).toList()
        val adapter = OptionSpinnerAdapter(requireContext(),android.R.layout.simple_spinner_item, spinnerList)
        binding.fragmentCustomerUpsertSpinner.adapter = adapter

        binding.fragmentCustomerUpsertSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (!binding.fragmentCustomerUpsertSpinner.getItemIdAtPosition(position).equals("고객님의 멤버십 정보 선택")) {

                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

}