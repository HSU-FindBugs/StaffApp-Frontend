package com.example.findbug.ui.customer_manage.vp

import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.findbug.R
import com.example.findbug.base.BaseFragment
import com.example.findbug.databinding.FragmentAddCustomerBinding
import com.example.findbug.domain.model.request.Address
import com.example.findbug.domain.model.request.MemberRegisterRequestDto
import com.example.findbug.ui.customer_manage.CustomerViewModel
import com.example.findbug.utils.extension.navigateSafe
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class CustomerAddCustomerFragment :
    BaseFragment<FragmentAddCustomerBinding>(R.layout.fragment_add_customer) {

    private lateinit var name: String
    private lateinit var phoneNumber: String
    private lateinit var address1: String
    private lateinit var address2: String
    private lateinit var address3: String
    private lateinit var detailAddress1: String
    private lateinit var detailAddress2: String
    private var membership by Delegates.notNull<Int>()
    private lateinit var address: Address
    private lateinit var memberRegisterRequestDto: MemberRegisterRequestDto

    private val customerViewModel : CustomerViewModel by activityViewModels()

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
                    addCustomer()
                }
            }
        }
    }

    // 전화번호 형식 변환 함수
    private fun formatPhoneNumber(phone: String): String {
        return if (phone.length == 11 && phone.startsWith("010")) {
            phone.replaceFirst("(\\d{3})(\\d{4})(\\d{4})".toRegex(), "$1-$2-$3")
        } else {
            phone
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

    private fun addCustomer() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    with(binding) {
                        name = fragmentCustomerUpsertNameEt.text.toString()
                        phoneNumber =
                            formatPhoneNumber(fragmentCustomerUpsertPhoneEt.text.toString())

                        val fullAddress = fragmentCustomerUpsertAddressEt.text.toString()
                        val (addr1, addr2, addr3) = splitAddress(fullAddress)
                        address1 = addr1
                        address2 = addr2
                        address3 = addr3

                        val detailAddress = fragmentCustomerUpsertDetailAddressEt.text.toString()
                        detailAddress1 = detailAddress.substring(0, 5)
                        detailAddress2 = if (detailAddress.length > 5) { detailAddress.substring(5) } else { "" }

                        val selectedRadioButtonId = fragmentCustomerUpsertRadioGroup.checkedRadioButtonId
                        if (selectedRadioButtonId != -1) {
                            val selectedRadioButton = binding.root.findViewById<RadioButton>(selectedRadioButtonId)
                            val selectedText = selectedRadioButton.text.toString()

                            when (selectedText) {
                                "1개월" -> membership = 1
                                "3개월" -> membership = 3
                                "6개월" -> membership = 6
                                "1년" -> membership = 12
                            }
                        }
                    }
                    address = Address(address1, address2, address3, detailAddress1, detailAddress2)
                    memberRegisterRequestDto = MemberRegisterRequestDto(1, name, "dssn1999@naver.com", phoneNumber, membership, address)

                    // 고객 등록 요청 API 호출
                    customerViewModel.registerCustomer(memberRegisterRequestDto)
                }

                // 고객 등록 되면 화면 전환
               customerViewModel.customerSaveResponse.collect() { res ->
                   if (res.body()?.checked == true) {
                       val action = CustomerHomeFragmentDirections.actionCustomerHomeFragmentToCustomerConfirmFragment()
                       findNavController().navigateSafe(action.actionId)
                   }
               }
            }
        }
    }

    // 버튼 활성화 함수
    private fun activateBtn() {
        with(binding) {
            val saveBtn = fragmentCustomerUpsertSaveBtn
            val radioGroup = fragmentCustomerUpsertRadioGroup
            val buttonBackground = R.drawable.shape_rounded_rect_50dp
            val enableButtonTextColor = ContextCompat.getColor(requireContext(), R.color.white)
            val disableButtonTextColor = ContextCompat.getColor(requireContext(), R.color.Blue500)

            // 버튼 활성화 여부를 조건 처리하는 로컬 함수
            fun updateSaveButtonState() {
                val nameEt = fragmentCustomerUpsertNameEt.text.toString()
                val phoneEt = fragmentCustomerUpsertPhoneEt.text.toString()
                val addressEt = fragmentCustomerUpsertAddressEt.text.toString()
                val detailAddressEt = fragmentCustomerUpsertDetailAddressEt.text.toString()
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
            fragmentCustomerUpsertNameEt.addTextChangedListener(textWatcher)
            fragmentCustomerUpsertPhoneEt.addTextChangedListener(textWatcher)
            fragmentCustomerUpsertPhoneEt.addTextChangedListener(PhoneNumberFormattingTextWatcher())
            fragmentCustomerUpsertAddressEt.addTextChangedListener(textWatcher)
            fragmentCustomerUpsertDetailAddressEt.addTextChangedListener(textWatcher)

            // RadioGroup에 선택 변경 리스너 추가
            radioGroup.setOnCheckedChangeListener { _, _ ->
                updateSaveButtonState() // 라디오 버튼 선택할 때 마다 버튼 상태 업데이트
            }
        }
    }

}