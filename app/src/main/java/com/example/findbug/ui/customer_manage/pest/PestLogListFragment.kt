package com.example.findbug.ui.customer_manage.pest

import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentPestLogBinding
import com.example.findbug.databinding.FragmentPestLogListBinding
import com.example.findbug.ui.customer_manage.CustomerViewModel
import com.example.findbug.utils.listener.RVClickListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PestLogListFragment : BaseFragment<FragmentPestLogListBinding>(R.layout.fragment_pest_log_list), RVClickListener {

    private val customerViewModel : CustomerViewModel by activityViewModels()
    private var memberId: Long = 0

    private lateinit var pestLogListRVAdapter: PestLogListRVAdapter

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        setToolbarNavigation(binding.fragmentPestLogListToolbar.toolbarPreviousIb)
        getPostId()
        observeViewModel()
        initAdapter()
    }

    private fun getPostId() {
        arguments?.let {
            memberId = it.getLong("memberId")?.toLong() ?: 1
        }
    }

    private fun initAdapter() {
        pestLogListRVAdapter = PestLogListRVAdapter(this)
        binding.fragmentPestLogPestListRv.adapter = pestLogListRVAdapter
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                customerViewModel.getPestLog(1, memberId)
                customerViewModel.detectionHistoryResponse.collect() { res ->
                    pestLogListRVAdapter.submitList(res.body()?.detectionHistoryDtoList)
                }
            }
        }
    }

    override fun onItemClick(item: Any) {
        TODO("Not yet implemented")
    }

}