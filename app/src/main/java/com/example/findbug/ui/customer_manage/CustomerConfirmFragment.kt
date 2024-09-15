package com.example.findbug.ui.customer_manage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentCustomerConfirmBinding
import com.example.findbug.utils.extension.navigateSafe

class CustomerConfirmFragment : BaseFragment<FragmentCustomerConfirmBinding
        >(R.layout.fragment_customer_confirm) {

    override fun setLayout() {
        initButton()
    }

    private fun initButton() {

        // 저장하기 버튼 누르면 방문완료 버튼 보여짐 및 저장하기 버튼 안보임
        with(binding) {
            fragmentCustomerConfirmSaveBtn.setOnClickListener {
                binding.fragmentCustomerConfirmVisitTv.visibility = View.VISIBLE
                binding.fragmentCustomerConfirmSaveBtn.visibility = View.INVISIBLE
            }

            // 연필 모양 버튼 누르면 고객 특이사항 작성 페이지로 이동 (작성 및 수정)
            fragmentCustomerConfirmEditSignificantIb.setOnClickListener {
                val action =
                    CustomerConfirmFragmentDirections.actionCustomerConfirmFragmentToCustomerReportFragment()
                findNavController().navigateSafe(action.actionId)
            }

            // 해충 기록 화면으로 이동하는 버튼 (임시버튼)
            fragmentCustomerConfirmBugSheetIb.setOnClickListener {
                val action =
                    CustomerConfirmFragmentDirections.actionCustomerConfirmFragmentToPestLogFragment()
                findNavController().navigateSafe(action.actionId)
            }

            // 감지 영상 목록 화면으로 이동하는 버튼
            fragmentCustomerConfirmVideoListIb.setOnClickListener {
                val action = CustomerConfirmFragmentDirections.actionCustomerConfirmFragmentToDetectionVideoListFragment()
                findNavController().navigateSafe(action.actionId)
            }

            // 실시간 영상 보기 화면으로 이동하는 버튼
            fragmentCustomerConfirmLiveIb.setOnClickListener {

            }

            // 카메라 추가 화면으로 이동하는 버튼
            fragmentCustomerConfirmAddCameraIb.setOnClickListener {
                val action = CustomerConfirmFragmentDirections.actionCustomerConfirmFragmentToCustomerAddCameraFragment()
                findNavController().navigateSafe(action.actionId)
            }
        }

    }

}