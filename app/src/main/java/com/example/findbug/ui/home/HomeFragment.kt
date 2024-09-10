package com.example.findbug.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentHomeBinding
import com.example.findbug.utils.extension.navigateSafe

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun setLayout() {
        initButton()
    }

    private fun initButton() {

        // 고객 확인 프래그먼트 가는 임시 버튼
        binding.fragmentCommuteBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCustomerConfirmFragment()
            findNavController().navigateSafe(action.actionId)
        }
    }

}