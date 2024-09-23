package com.example.findbug.ui.home

import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentHomeBinding
import com.example.findbug.domain.model.response.NotificationDto
import com.example.findbug.utils.dialog.NoticeDialog
import com.example.findbug.utils.listener.RVClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), RVClickListener {

    private lateinit var noticeListRVAdapter: NoticeListRVAdapter
    private val mainViewModel : MainViewModel by activityViewModels()

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        initButton()
        initAdapter()
        observerViewModel()
    }

    private fun initButton() {
        var commute = true
        val commuteBtn = binding.fragmentHomeCommuteBtn
        commuteBtn.setOnClickListener {
            if(commute){
                commuteBtn.background = ContextCompat.getDrawable(requireContext(), R.drawable.shape_rounded_rect_50dp)
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

    private fun initAdapter() {
        noticeListRVAdapter = NoticeListRVAdapter(this)
        binding.fragmentHomeNoticeRv.adapter = noticeListRVAdapter
    }

    private fun observerViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.getMainPage(1)
                mainViewModel.mainPageResponse.collect() { res ->
                    binding.mainPageResponse = res.body()
                    res.body()?.notificationDtoList?.let { notificationList ->
                        noticeListRVAdapter.submitList(notificationList)
                    }
                }
            }
        }
    }

    // 리사이클러뷰 아이템 클릭 리스너
    override fun onItemClick(item: Any) {
        if (item is NotificationDto) {
            item.title?.let { title ->
                item.content?.let { content ->
                    NoticeDialog(title, content).show(parentFragmentManager, "NoticeDialog")
                }
            }
        }
    }

}