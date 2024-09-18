package com.example.findbug.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentHomeBinding
import com.example.findbug.utils.extension.navigateSafe

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

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

        var commute = true
        val commuteBtn = binding.fragmentHomeCommuteBtn
        commuteBtn.setOnClickListener {
            if(commute){
                commuteBtn.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.shape_rounded_rect_50dp))
                commuteBtn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.Blue500))
                commuteBtn.text = "퇴근하기"
                commute = false
            } else {
                commuteBtn.background = ContextCompat.getDrawable(requireContext(), R.drawable.shape_rounded_rect_50dp)
                commuteBtn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.Blue700))
                commuteBtn.text = "출근하기"
                commute = true
            }
        }
    }

}