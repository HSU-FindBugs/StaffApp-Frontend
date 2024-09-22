package com.example.findbug.ui.customer_manage.detection

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentDetectionVideoListBinding
import com.example.findbug.ui.customer_manage.CustomerViewModel
import com.example.findbug.utils.listener.RVClickListener
import kotlinx.coroutines.launch

class DetectionVideoListFragment : BaseFragment<FragmentDetectionVideoListBinding>(R.layout.fragment_detection_video_list), RVClickListener {

    private val customerViewModel : CustomerViewModel by activityViewModels()
    private var memberId: Long = 0

    private lateinit var detectionVideoListRVAdapter: DetectionVideoListRVAdapter

    override fun setLayout() {
        initSettings()
        getPostId()
        observeViewModel()
        initAdapter()
    }

    private fun initSettings() {
        setToolbarNavigation(binding.fragmentDetectionVideoListToolbar.toolbarPreviousIb)
    }

    private fun getPostId() {
        arguments?.let {
            memberId = it.getLong("memberId")?.toLong() ?: 1
        }
    }

    private fun initAdapter() {
        detectionVideoListRVAdapter = DetectionVideoListRVAdapter(this)
        binding.fragmentDetectionVideoListRv.adapter = detectionVideoListRVAdapter
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                customerViewModel.getPestLog(1, memberId)
                customerViewModel.detectionHistoryResponse.collect() { res ->
                    detectionVideoListRVAdapter.submitList(res.body()?.detectionHistoryDtoList)
                }
            }
        }
    }

    override fun onItemClick(item: Any) {
        TODO("Not yet implemented")
    }

}