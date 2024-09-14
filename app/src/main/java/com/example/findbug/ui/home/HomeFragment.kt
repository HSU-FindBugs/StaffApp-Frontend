package com.example.findbug.ui.home

import android.os.Bundle
import android.util.Log
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

        // 해충 기록 화면 가는 임시 버튼
        binding.fragmentCommuteBtn.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToPestLogFragment()
            findNavController().navigateSafe(action.actionId)
        }
    }

}