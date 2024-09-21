package com.example.findbug.ui.customer_manage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentCustomerSearchBinding
import com.example.findbug.ui.customer_manage.vp.RecentCustomerRVAdapter
import com.example.findbug.utils.dialog.SearchDialog
import com.example.findbug.utils.extension.navigateSafe
import com.example.findbug.utils.listener.RVClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CustomerSearchFragment : BaseFragment<FragmentCustomerSearchBinding>(R.layout.fragment_customer_search), RVClickListener {

    private lateinit var recentCustomerRVAdapter: RecentCustomerRVAdapter
    private var isSuccess = false
    private var memberId = 0
    private val customerViewModel : CustomerViewModel by activityViewModels()

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
       // setToolbarNavigation(binding.fragmentCustomerSearchToolbar.toolbarPreviousIb)
        initAdapter()
        observeViewModel()
        initButtons()
    }

    private fun initButtons() {
        binding.fragmentCustomerSearchIb.setOnClickListener {
            searchCustomer()
        }
    }

    private fun initAdapter() {
        val layoutManager = GridLayoutManager(requireContext(), 3)
        recentCustomerRVAdapter = RecentCustomerRVAdapter(this)
        binding.fragmentCustomerRecentSearchRv.layoutManager = layoutManager
        binding.fragmentCustomerRecentSearchRv.adapter = recentCustomerRVAdapter
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                customerViewModel.getRecentCustomerSearchList(1)

                customerViewModel.recentCustomerSearchResponse.collect() { res ->
                    val recentCustomer = res.body()?.recentSearchResults
                    recentCustomerRVAdapter.submitList(recentCustomer)

                    // 최근 목록 비어있지 않으면
                    if (recentCustomer != null) {
                        if (recentCustomer.isNotEmpty()) {
                            binding.fragmentCustomerSearchEmptyIv.visibility = View.GONE
                            binding.fragmentCustomerSearchEmptyTv.visibility = View.GONE
                            binding.fragmentCustomerRecentSearchRv.visibility = View.VISIBLE
                        } else {
                            binding.fragmentCustomerSearchEmptyIv.visibility = View.VISIBLE
                            binding.fragmentCustomerSearchEmptyTv.visibility = View.VISIBLE
                            binding.fragmentCustomerRecentSearchRv.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    // 고객 검색 (고객 정보 확인으로 이동)
    private fun searchCustomer() {
        lifecycleScope.launch {
            // 고객 검색 요청
            customerViewModel.customerInfoSearch(1, binding.fragmentCustomerSearchEt.text.toString())

            // 검색 결과 수집
            customerViewModel.customerSearchResponse.collectLatest { res ->
                val responseBody = res.body()
                memberId = responseBody?.managementPageMemberDtoList?.firstOrNull()?.id ?: 0
                Log.d("고객 검색", "memberId : $memberId")
                val args = Bundle().apply { putInt("memberId", memberId) }

                // res.body()가 null이 아닌지 체크하고, 검색된 결과가 있을 때만 화면 전환
                if (res.isSuccessful && responseBody != null && responseBody.isSearched == true) {
                    Log.d("고객 검색", "isSearched : ${responseBody.isSearched}")
                    val action = CustomerSearchFragmentDirections.actionCustomerSearchFragmentToCustomerConfirmFragment()
                    findNavController().navigateSafe(action.actionId, args)
                }
                // 검색 결과가 없거나 isSearched가 false인 경우에만 다이얼로그 표시
                else if (responseBody != null && responseBody.isSearched == false) {
                    Log.d("고객 검색", "isSuccess : 실패")
                    showSearchDialog(childFragmentManager)
                }
                // res.body()가 null이거나 잘못된 경우 아무 동작도 하지 않음
                else {
                    Log.d("고객 검색", "Invalid response or no search result")
                    // 다이얼로그가 뜨지 않도록 그냥 넘어감
                }
            }
        }
    }


    // 최근 검색 고객 목록에서 고객 클릭시 해당 고객 검색
    override fun onItemClick(item: Any) { }

    private var searchDialog: SearchDialog? = null  // 다이얼로그 상태 추적

    private fun showSearchDialog(fragmentManager: FragmentManager) {
        // 다이얼로그가 없거나 이미 사라졌을 때만 새로 생성
        if (searchDialog == null || !searchDialog!!.isAdded) {
            searchDialog = SearchDialog()
            searchDialog!!.show(fragmentManager, "SearchDialog")
        }
    }

}