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
        binding.fragmentCustomerConfirmSaveBtn.setOnClickListener {
            binding.fragmentCustomerConfirmVisitTv.visibility = View.VISIBLE
            binding.fragmentCustomerConfirmSaveBtn.visibility = View.INVISIBLE
        }

        // 연필 모양 버튼 누르면 고객 특이사항 작성 페이지로 이동
        binding.fragmentCustomerConfirmEditSignificantIb.setOnClickListener {
            val action = CustomerConfirmFragmentDirections.actionCustomerConfirmFragmentToCustomerReportFragment()
            findNavController().navigateSafe(action.actionId)
        }

    }

}