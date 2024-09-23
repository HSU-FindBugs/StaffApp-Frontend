package com.example.findbug.ui.customer_manage.pest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentPestLogBinding
import com.example.findbug.databinding.FragmentPestLogListBinding
import com.example.findbug.domain.model.response.DetectionHistory
import com.example.findbug.ui.customer_manage.CustomerViewModel
import com.example.findbug.ui.home.MainViewModel
import com.example.findbug.utils.extension.navigateSafe
import com.example.findbug.utils.listener.RVClickListener
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PestLogListFragment : BaseFragment<FragmentPestLogListBinding>(R.layout.fragment_pest_log_list), RVClickListener {

    private val mainViewModel : MainViewModel by activityViewModels()
    private var memberId: Long = 0
    private var detectionHistoryId: Long = 0

    private lateinit var pestLogListRVAdapter: PestLogListRVAdapter

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        setToolbarNavigation(binding.fragmentPestLogListToolbar.toolbarPreviousIb)
        getPostId()
        initAdapter()
        observeViewModel()
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
                mainViewModel.getBugRecordList(memberId)
                mainViewModel.detectionHistory.collect() { res ->
                    pestLogListRVAdapter.submitList(res.body())
                }
            }
        }
    }

    override fun onItemClick(item: Any) {
        if (item is DetectionHistory) {
            val args = Bundle().apply {
                item.id?.let { putLong("detectionHistoryId", it) }
            }
            val action = PestLogListFragmentDirections.actionPestLogListFragmentToPestLogFragment()
            findNavController().navigateSafe(action.actionId, args)
        }
    }

}