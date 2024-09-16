package com.example.findbug.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentProfileHomeBinding
import com.example.findbug.utils.extension.navigateSafe

class ProfileHomeFragment : BaseFragment<FragmentProfileHomeBinding>(R.layout.fragment_profile_home) {

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        initButtons()
    }

    private fun initButtons() {

        with(binding) {
            val action = ProfileHomeFragmentDirections.actionProfileHomeFragmentToChangeWorkspaceFragment()

            fragmentProfileHomeChangeWorkplaceTv.setOnClickListener {
                findNavController().navigateSafe(action.actionId)
            }

            fragmentProfileHomeChangeLogoutIb.setOnClickListener {
                findNavController().navigateSafe(action.actionId)
            }

            fragmentProfileHomeCl.setOnClickListener {
                findNavController().navigateSafe(action.actionId)
            }

            // 로그아웃 텍스트뷰
            fragmentProfileHomeChangeLogoutTv.setOnClickListener {

            }

            // 로그아웃 버튼
            fragmentProfileHomeChangeLogoutIb.setOnClickListener {

            }
        }
    }

}