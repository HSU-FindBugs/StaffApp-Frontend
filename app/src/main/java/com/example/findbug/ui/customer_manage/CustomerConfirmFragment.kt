package com.example.findbug.ui.customer_manage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentCustomerConfirmBinding

class CustomerConfirmFragment : BaseFragment<FragmentCustomerConfirmBinding
        >(R.layout.fragment_customer_confirm) {

    override fun setLayout() {
        binding.fragmentCustomerConfirmSaveBtn.setOnClickListener {
            Toast.makeText(requireContext(), "저장", Toast.LENGTH_SHORT).show()
        }
    }

}