package com.example.findbug.ui.customer_manage

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentCustomerReportEditBinding
import com.example.findbug.domain.model.request.ManagementProfileUpdateNoteRequestDto
import com.example.findbug.utils.extension.navigateSafe
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CustomerReportEditFragment : BaseFragment<FragmentCustomerReportEditBinding>(R.layout.fragment_customer_report_edit) {

    private lateinit var memo: String

    private val customerViewModel : CustomerViewModel by activityViewModels()
    private var memberId: Long = 0

    override fun setLayout() {
        setToolbarNavigation(binding.fragmentCustomerReportToolbar.toolbarPreviousIb)
        initSettings()
    }

    private fun initSettings() {
        getPostId()
        getMemo()
        initButton()
    }

    // 특이사항 메모 불러오기
    private fun getMemo() {
        binding.memo = memo
    }

    private fun getPostId() {
        arguments?.let {
            memberId = it.getLong("memberId")?.toLong() ?: 1
            memo = it.getString("memo") ?: ""
        }
    }

    private fun initButton() {
        binding.fragmentCustomerReportSaveBtn.setOnClickListener {
            updateMemo()
        }
    }

    private fun updateMemo() {
        lifecycleScope.launch {
            val managementProfileUpdateNoteRequestDto = ManagementProfileUpdateNoteRequestDto(binding.fragmentCustomerReportEt.text.toString())
            customerViewModel.updateCustomerParticular(1, memberId, managementProfileUpdateNoteRequestDto)
            customerViewModel.customerParticularResponse.collectLatest { res ->
                if(res.isSuccessful) {
                    val action = CustomerReportEditFragmentDirections.actionCustomerReportFragmentToCustomerConfirmFragment()
                    val args = Bundle().apply { putLong("memberId", memberId) }
                    findNavController().navigateSafe(action.actionId, args)
                }
            }
        }
    }

}