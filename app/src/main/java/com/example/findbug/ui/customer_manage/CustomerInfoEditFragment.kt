package com.example.findbug.ui.customer_manage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentCustomerInfoEditBinding
import com.example.findbug.domain.model.request.Address
import com.example.findbug.domain.model.request.MemberUpdateRequestDto
import com.example.findbug.utils.extension.navigateSafe
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CustomerInfoEditFragment :
    BaseFragment<FragmentCustomerInfoEditBinding>(R.layout.fragment_customer_info_edit) {

    private val customerViewModel: CustomerViewModel by activityViewModels()

    private var memberId: Long = 0

    private lateinit var address1: String
    private lateinit var address2: String
    private lateinit var address3: String
    private lateinit var detailAddress1: String
    private lateinit var detailAddress2: String

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        activateBtn()
        getPostId()
        observeViewModel()
        initButtons()
    }

    private fun getPostId() {
        arguments?.let {
            memberId = it.getLong("memberId")?.toLong() ?: 1
        }
    }

    // 버튼 클릭하면 회원 정보 업데이트
    private fun initButtons() {
        with(binding) {
            fragmentCustomerInfoEditSaveBtn.setOnClickListener {
                if (fragmentCustomerInfoEditSaveBtn.isEnabled) {
                    updateCustomerInfo()
                }
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                customerViewModel.getMemberProfile(memberId)
                customerViewModel.memberProfileResponse.collect() { res ->
                    binding.managementPageMember = res.body()
                }
            }
        }
    }

    // 주소 분리 함수
    private fun splitAddress(address: String): Triple<String, String, String> {

        val addressParts = address.split(" ")
        val address1 = addressParts.getOrElse(0) { "" }
        val address2 = addressParts.getOrElse(1) { "" }
        val address3 = addressParts.getOrElse(2) { "" }

        return Triple(address1, address2, address3)
    }

    // 고객 정보 업데이트 API 호출 함수
    private fun updateCustomerInfo() {

        var membership = 0

        // 지역 주소
        val fullAddress = binding.fragmentCustomerInfoEditAddressEt.text.toString()
        val (addr1, addr2, addr3) = splitAddress(fullAddress)
        address1 = addr1
        address2 = addr2
        address3 = addr3

        // 상세 주소
        val detailAddress = binding.fragmentCustomerInfoEditDetailAddressEt.text.toString()
        detailAddress1 = detailAddress.substring(0, 5)
        detailAddress2 = if (detailAddress.length > 5) {
            detailAddress.substring(5)
        } else {
            ""
        }

        // 멤버십 (라디오 버튼)
        val selectedRadioButtonId = binding.fragmentCustomerInfoEditRadioGroup.checkedRadioButtonId
        if (selectedRadioButtonId != -1) {
            val selectedRadioButton = binding.root.findViewById<RadioButton>(selectedRadioButtonId)
            val selectedText = selectedRadioButton.text.toString()

            membership = when (selectedText) {
                "1개월" -> 1
                "3개월" -> 3
                "6개월" -> 6
                "1년" -> 12
                else -> 0 // 기본값
            }
        }

        val args = Bundle().apply {
            putLong("memberId", memberId)
        }

        lifecycleScope.launch {
            val address = Address(address1, address2, address3, detailAddress1, detailAddress2)
            val memberUpdateRequestDto = MemberUpdateRequestDto(
                1,
                memberId,
                binding.fragmentCustomerInfoEditNameEt.text.toString(),
                "",
                binding.fragmentCustomerInfoEditPhoneEt.text.toString(),
                membership,
                address
            )
            customerViewModel.updateCustomerInfo(memberUpdateRequestDto)
            customerViewModel.customerInfo.collect() { res ->
                if(res.isSuccessful) {
                    val action = CustomerInfoEditFragmentDirections.actionCustomerInfoEditFragmentToCustomerConfirmFragment()
                    findNavController().navigateSafe(action.actionId, args)
                }
            }
        }
    }

    // 버튼 활성화 함수
    private fun activateBtn() {
        with(binding) {
            val saveBtn = fragmentCustomerInfoEditSaveBtn
            val radioGroup = binding.fragmentCustomerInfoEditRadioGroup
            val buttonBackground = R.drawable.shape_rounded_rect_50dp
            val enableButtonTextColor = ContextCompat.getColor(requireContext(), R.color.white)
            val disableButtonTextColor = ContextCompat.getColor(requireContext(), R.color.Blue500)

            // 버튼 활성화 여부를 조건 처리하는 로컬 함수
            fun updateSaveButtonState() {
                val nameEt = fragmentCustomerInfoEditNameEt.text.toString()
                val phoneEt = fragmentCustomerInfoEditPhoneEt.text.toString()
                val addressEt = fragmentCustomerInfoEditDetailAddressEt.text.toString()
                val detailAddressEt = fragmentCustomerInfoEditDetailAddressEt.text.toString()
                val isRadioButtonSelected = radioGroup.checkedRadioButtonId != -1

                if (nameEt.isNotEmpty() && phoneEt.isNotEmpty() && addressEt.isNotEmpty() && detailAddressEt.isNotEmpty() && isRadioButtonSelected) {
                    saveBtn.setBackgroundResource(buttonBackground)
                    saveBtn.setBackgroundTintList(
                        ContextCompat.getColorStateList(
                            requireContext(),
                            R.color.Blue700
                        )
                    )
                    saveBtn.setTextColor(enableButtonTextColor)
                    saveBtn.isEnabled = true
                } else {
                    saveBtn.setBackgroundResource(buttonBackground)
                    saveBtn.setBackgroundTintList(
                        ContextCompat.getColorStateList(
                            requireContext(),
                            R.color.Blue300
                        )
                    )
                    saveBtn.setTextColor(disableButtonTextColor)
                    saveBtn.isEnabled = false
                }
            }

            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    updateSaveButtonState() // 텍스트 변경 시 버튼 상태 업데이트
                }
            }

            // EditText에 TextWatcher 추가
            fragmentCustomerInfoEditNameEt.addTextChangedListener(textWatcher)
            fragmentCustomerInfoEditPhoneEt.addTextChangedListener(textWatcher)
            fragmentCustomerInfoEditAddressEt.addTextChangedListener(textWatcher)
            fragmentCustomerInfoEditDetailAddressEt.addTextChangedListener(textWatcher)

            // RadioGroup에 선택 변경 리스너 추가
            radioGroup.setOnCheckedChangeListener { _, _ ->
                updateSaveButtonState() // 라디오 버튼 선택할 때 마다 버튼 상태 업데이트
            }
        }
    }

}