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
        initButton()
    }

    private fun initButton() {

    }

}