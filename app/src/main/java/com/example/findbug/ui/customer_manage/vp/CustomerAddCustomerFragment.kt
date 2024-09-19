package com.example.findbug.ui.customer_manage.vp

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentAddCustomerBinding
import com.example.findbug.utils.extension.navigateSafe

class CustomerAddCustomerFragment :
    BaseFragment<FragmentAddCustomerBinding>(R.layout.fragment_add_customer) {

    override fun setLayout() {
        initSettings()
    }

    private fun initSettings() {
        activateBtn()
        initButtons()
    }

    private fun initButtons() {
        with(binding) {
            fragmentCustomerUpsertSaveBtn.setOnClickListener {
                if (fragmentCustomerUpsertSaveBtn.isEnabled) {
                    val action = CustomerHomeFragmentDirections.actionCustomerHomeFragmentToCustomerConfirmFragment()
                    findNavController().navigateSafe(action.actionId)
                }
            }
        }
    }

    // 버튼 활성화 함수
    private fun activateBtn() {
        val saveBtn = binding.fragmentCustomerUpsertSaveBtn
        val radioGroup = binding.fragmentCustomerUpsertRadioGroup
        val buttonBackground = R.drawable.shape_rounded_rect_50dp
        val enableButtonTextColor = ContextCompat.getColor(requireContext(), R.color.white)
        val disableButtonTextColor = ContextCompat.getColor(requireContext(), R.color.Blue500)

        // 버튼 활성화 여부를 조건 처리하는 로컬 함수
        fun updateSaveButtonState() {
            val nameEt = binding.fragmentCustomerUpsertNameEt.text.toString()
            val phoneEt = binding.fragmentCustomerUpsertPhoneEt.text.toString()
            val addressEt = binding.fragmentCustomerUpsertAddressEt.text.toString()
            val detailAddressEt = binding.fragmentCustomerUpsertDetailAddressEt.text.toString()
            val isRadioButtonSelected = radioGroup.checkedRadioButtonId != -1

            if (nameEt.isNotEmpty() && phoneEt.isNotEmpty() && addressEt.isNotEmpty() && detailAddressEt.isNotEmpty() && isRadioButtonSelected) {
                saveBtn.setBackgroundResource(buttonBackground)
                saveBtn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.Blue700))
                saveBtn.setTextColor(enableButtonTextColor)
                saveBtn.isEnabled = true
            } else {
                saveBtn.setBackgroundResource(buttonBackground)
                saveBtn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.Blue300))
                saveBtn.setTextColor(disableButtonTextColor)
                saveBtn.isEnabled = false
            }
        }

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                updateSaveButtonState() // 텍스트 변경 시 버튼 상태 업데이트
            }
        }

        // EditText에 TextWatcher 추가
        binding.fragmentCustomerUpsertNameEt.addTextChangedListener(textWatcher)
        binding.fragmentCustomerUpsertPhoneEt.addTextChangedListener(textWatcher)
        binding.fragmentCustomerUpsertAddressEt.addTextChangedListener(textWatcher)
        binding.fragmentCustomerUpsertDetailAddressEt.addTextChangedListener(textWatcher)

        // RadioGroup에 선택 변경 리스너 추가
        radioGroup.setOnCheckedChangeListener { _, _ ->
            updateSaveButtonState() // 라디오 버튼 선택할 때 마다 버튼 상태 업데이트
        }
    }


}