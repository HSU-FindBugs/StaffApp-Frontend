package com.example.findbug.ui.customer_manage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentCustomerConfirmBinding
import com.example.findbug.utils.extension.navigateSafe
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CustomerConfirmFragment : BaseFragment<FragmentCustomerConfirmBinding
        >(R.layout.fragment_customer_confirm) {

    private lateinit var visitStatus: String

    private val customerViewModel : CustomerViewModel by activityViewModels()
    private var memberId: Long = 0

    override fun setLayout() {
        setToolbarNavigation(binding.fragmentCustomerConfirmToolbar.toolbarPreviousIb)
        getPostId()
        observeViewModel()
        initButton()
    }

    private fun getPostId() {
        arguments?.let {
            memberId = it.getLong("memberId")?.toLong() ?: 1
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                customerViewModel.registerCustomerVisit(1, memberId)
                customerViewModel.customerProfileResponse.collect() { res ->
                    binding.profilePage = res.body()?.managementProfilePageMemberDto
                    binding.profilePageVisit = res.body()?.managementProfilePageVisitDto
                    visitStatus = res.body()?.managementProfilePageMemberDto?.visitStatus.toString()
                }
            }
        }
    }

    private fun initButton() {

        // 저장하기 버튼 누르면 방문완료 버튼 보여짐 및 저장하기 버튼 안보임
        with(binding) {
            fragmentCustomerConfirmSaveBtn.setOnClickListener {
                when(visitStatus) {
                    "방문 완료" -> {
                        binding.fragmentCustomerConfirmVisitTv.visibility = View.VISIBLE    // 방문 완료
                        binding.fragmentCustomerConfirmSaveBtn.visibility = View.GONE       // 저장하기 버튼
                    }
                    "방문 필요" -> {
                        binding.fragmentCustomerConfirmVisitTv.visibility = View.GONE        // 방문 완료
                        binding.fragmentCustomerConfirmSaveBtn.visibility = View.VISIBLE  // 저장하기 버튼
                    }
                }
                customerViewModel.registerCustomerVisit(1, memberId) // 고객 방문 등록 API
            }

            val args = Bundle().apply {
                putLong("memberId", memberId)
                putString("memo", binding.fragmentCustomerConfirmDescTv.text.toString())
            }

            // 연필 모양 버튼 누르면 고객 특이사항 작성 페이지로 이동 (작성 및 수정) (멤버아이디 전달)
            fragmentCustomerConfirmEditSignificantIb.setOnClickListener {
                val action =
                    CustomerConfirmFragmentDirections.actionCustomerConfirmFragmentToCustomerReportFragment()
                findNavController().navigateSafe(action.actionId, args)
            }

            // 해충 기록 목록 화면으로 이동하는 버튼
            fragmentCustomerConfirmBugSheetIb.setOnClickListener {
                val action = CustomerConfirmFragmentDirections.actionCustomerConfirmFragmentToPestLogListFragment()
                findNavController().navigateSafe(action.actionId)
            }

            // 감지 사진 목록 화면으로 이동하는 버튼
            fragmentCustomerConfirmVideoListIb.setOnClickListener {
                val action = CustomerConfirmFragmentDirections.actionCustomerConfirmFragmentToDetectionVideoListFragment()
                findNavController().navigateSafe(action.actionId)
            }

            // 실시간 영상 보기 화면으로 이동하는 버튼
            fragmentCustomerConfirmLiveIb.setOnClickListener {
                val action = CustomerConfirmFragmentDirections.actionCustomerConfirmFragmentToLiveStreamFragment()
                findNavController().navigateSafe(action.actionId)
            }

            // 카메라 등록 화면으로 이동하는 버튼
            fragmentCustomerConfirmAddCameraIb.setOnClickListener {
                val action = CustomerConfirmFragmentDirections.actionCustomerConfirmFragmentToCustomerAddCameraFragment()
                findNavController().navigateSafe(action.actionId)
            }

            // 회원 정보 수정 화면으로 이동하는 버튼 (멤버아이디 전달)
            fragmentCustomerConfirmEditCustomerInfoIb.setOnClickListener {
                val action = CustomerConfirmFragmentDirections.actionCustomerConfirmFragmentToCustomerInfoEditFragment()
                findNavController().navigateSafe(action.actionId, args)
            }
        }

    }

}