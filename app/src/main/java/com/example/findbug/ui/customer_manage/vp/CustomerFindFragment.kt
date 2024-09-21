package com.example.findbug.ui.customer_manage.vp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentCustomerFindBinding
import com.example.findbug.domain.model.response.ManagementPageMemberDto
import com.example.findbug.ui.customer_manage.CustomerViewModel
import com.example.findbug.utils.extension.navigateSafe
import com.example.findbug.utils.listener.RVClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CustomerFindFragment : BaseFragment<FragmentCustomerFindBinding>(R.layout.fragment_customer_find), RVClickListener {

    private lateinit var customerListAdapter: CustomerListRVAdapter
    private val customerViewModel : CustomerViewModel by activityViewModels()

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        goSearchPage()
        initAdapter()
        observeViewModel()
    }

    private fun goSearchPage() {

        val action = CustomerHomeFragmentDirections.actionCustomerHomeFragmentToCustomerSearchFragment()
        with(binding) {
            fragmentCustomerFindSearchTv.setOnClickListener {
                findNavController().navigateSafe(action.actionId)
            }
            fragmentCustomerFindSearchIb.setOnClickListener {
                findNavController().navigateSafe(action.actionId)
            }
        }
    }

    private fun initAdapter() {
        customerListAdapter = CustomerListRVAdapter(this)
        binding.fragmentCustomerFindRv.adapter = customerListAdapter
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    customerViewModel.getCustomerList(1, 0)
                }
                customerViewModel.customerListResponse.collect() { res ->
                    if (res.isSuccessful) {
                        customerListAdapter.submitList(res.body()?.managementPageMemberDtoList)
                    }
                }
            }
        }
    }

    // 고객 정보 확인 페이지로 이동 (고객 아이디 전달)
    override fun onItemClick(item: Any) {
        if (item is ManagementPageMemberDto) {
            val action = CustomerHomeFragmentDirections.actionCustomerHomeFragmentToCustomerConfirmFragment()
            val args = Bundle().apply {
                putInt("customerId", item.id ?: 1)
            }
            findNavController().navigateSafe(action.actionId, args)
        }
    }

}