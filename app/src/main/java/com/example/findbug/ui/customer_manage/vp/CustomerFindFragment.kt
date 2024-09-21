package com.example.findbug.ui.customer_manage.vp

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentCustomerFindBinding
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
                    Log.d("ㄹㄹㄹㄹㄹㄹ","res message: ${res.isSuccessful}")
                    customerListAdapter.submitList(res.body()?.managementPageMemberDtoList)
                }
            }
        }
    }

    // 고객 정보 확인 페이지로 이동
    override fun onItemClick(item: Any) {
        TODO("Not yet implemented")
    }

}