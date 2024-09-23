package com.example.findbug.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentProfileHomeBinding
import com.example.findbug.ui.home.MainViewModel
import com.example.findbug.utils.extension.navigateSafe
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileHomeFragment : BaseFragment<FragmentProfileHomeBinding>(R.layout.fragment_profile_home) {

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        initButtons()
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.getProfile(1)
                mainViewModel.profileResponse.collect() { res ->
                    binding.profileResponse = res.body()
                }
            }
        }
    }

    private fun initButtons() {

        with(binding) {
            val action = ProfileHomeFragmentDirections.actionProfileHomeFragmentToChangeWorkspaceFragment()

            fragmentProfileHomeChangeWorkplaceTv.setOnClickListener {
                findNavController().navigateSafe(action.actionId)
            }

            fragmentProfileHomeChangeWorkplaceIb.setOnClickListener {
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