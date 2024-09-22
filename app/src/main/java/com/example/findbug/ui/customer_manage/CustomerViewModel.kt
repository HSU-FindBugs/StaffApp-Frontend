package com.example.findbug.ui.customer_manage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findbug.domain.model.request.ManagementProfileUpdateNoteRequestDto
import com.example.findbug.domain.model.request.MemberRegisterRequestDto
import com.example.findbug.domain.model.request.MemberUpdateRequestDto
import com.example.findbug.domain.model.response.ManagementPageMemberDto
import com.example.findbug.domain.model.response.ManagementPageRecentSearchResponse
import com.example.findbug.domain.model.response.ManagementPageResponse
import com.example.findbug.domain.model.response.ManagementPageSaveResponse
import com.example.findbug.domain.model.response.ManagementProfileResponse
import com.example.findbug.domain.model.response.ManagementProfileSaveResponse
import com.example.findbug.domain.repository.CustomerApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val customerApiRepository: CustomerApiRepository
) : ViewModel() {

    private val _customerInfo = MutableStateFlow<Response<String>>(Response.success(""))
    val customerInfo: MutableStateFlow<Response<String>> = _customerInfo

    private val _customerSaveResponse = MutableStateFlow(Response.success(ManagementPageSaveResponse()))
    val customerSaveResponse: MutableStateFlow<Response<ManagementPageSaveResponse>> = _customerSaveResponse

    private val _customerListResponse = MutableStateFlow(Response.success(ManagementPageResponse(null)))
    val customerListResponse: MutableStateFlow<Response<ManagementPageResponse>> = _customerListResponse

    private val _memberProfileResponse = MutableStateFlow(Response.success(ManagementPageMemberDto()))
    val memberProfileResponse: MutableStateFlow<Response<ManagementPageMemberDto>> = _memberProfileResponse

    private val _customerSearchResponse = MutableStateFlow(Response.success(ManagementPageResponse(null)))
    val customerSearchResponse: MutableStateFlow<Response<ManagementPageResponse>> = _customerSearchResponse

    private val _recentCustomerSearchResponse = MutableStateFlow(Response.success(ManagementPageRecentSearchResponse()))
    val recentCustomerSearchResponse: MutableStateFlow<Response<ManagementPageRecentSearchResponse>> = _recentCustomerSearchResponse

    private val _customerProfileResponse = MutableStateFlow(Response.success(ManagementProfileResponse()))
    val customerProfileResponse: MutableStateFlow<Response<ManagementProfileResponse>> = _customerProfileResponse

    private val _customerVisitResponse = MutableStateFlow(Response.success(ManagementProfileSaveResponse()))
    val customerVisitResponse: MutableStateFlow<Response<ManagementProfileSaveResponse>> = _customerVisitResponse

    private val _customerParticularResponse = MutableStateFlow(Response.success(ManagementProfileSaveResponse()))
    val customerParticularResponse: MutableStateFlow<Response<ManagementProfileSaveResponse>> = _customerParticularResponse

    // 회원 정보 업데이트
    fun updateCustomerInfo(memberUpdateRequestDto: MemberUpdateRequestDto) {
        viewModelScope.launch {
            try {
                customerApiRepository.updateCustomerInfo(memberUpdateRequestDto).collect { response ->
                    _customerInfo.value = response
                }
            } catch (e: Exception) {
                Log.e("CustomerViewModel updateCustomerInfo Error", e.message.toString())
            }
        }
    }

    // 고객 등록
    fun registerCustomer(memberRegisterRequestDto: MemberRegisterRequestDto) {
        viewModelScope.launch {
            try {
                customerApiRepository.registerCustomer(memberRegisterRequestDto).collect { response ->
                    _customerSaveResponse.value = response
                }
            } catch (e: Exception) {
                Log.e("CustomerViewModel registerCustomer Error", e.message.toString())
            }
        }
    }

    // 고객 목록 조회
    fun getCustomerList(staffId: Long, page: Int) {
        viewModelScope.launch {
            try {
                customerApiRepository.getCustomerList(staffId, page).collect { response ->
                    _customerListResponse.value = response
                    Log.d("aaaa", "aaaa: ${response}")
                }
            } catch (e: Exception) {
                Log.e("CustomerViewModel getCustomerList Error", e.message.toString())
            }
        }
    }

    // 회원 프로필 정보 확인
    fun getMemberProfile(memberId: Long) {
        viewModelScope.launch {
            try {
                customerApiRepository.getMemberProfile(memberId).collect { response ->
                    _memberProfileResponse.value = response
                }
            } catch (e: Exception) {
                Log.e("CustomerViewModel getMemberProfile Error", e.message.toString())
            }
        }
    }

    // 고객 정보 검색
    fun customerInfoSearch(staffId: Long, memberName: String) {
        viewModelScope.launch {
            try {
                customerApiRepository.customerInfoSearch(staffId, memberName).collect { response ->
                    _customerSearchResponse.value = response
                }
            } catch (e: Exception) {
                Log.e("CustomerViewModel customerInfoSearch Error", e.message.toString())
            }
        }
    }

    // 사용자 최신 검색 기록 조회
    fun getRecentCustomerSearchList(staffId: Long) {
        viewModelScope.launch {
            try {
                customerApiRepository.getRecentCustomerSearchList(staffId).collect { response ->
                    _recentCustomerSearchResponse.value = response
                }
            } catch (e: Exception) {
                Log.e("CustomerViewModel getRecentCustomerSearchList Error", e.message.toString())
            }
        }
    }

    // 고객 프로필 조회
    fun getCustomerProfile(staffId: Long, memberId: Long) {
        viewModelScope.launch {
            try {
                customerApiRepository.getCustomerProfile(staffId, memberId).collect { response ->
                    _customerProfileResponse.value = response
                }
            } catch (e: Exception) {
                Log.e("CustomerViewModel getCustomerProfile Error", e.message.toString())
            }
        }
    }

    // 고객 방문 등록
    fun registerCustomerVisit(staffId: Long, memberId: Long) {
        viewModelScope.launch {
            try {
                customerApiRepository.registerCustomerVisit(staffId, memberId).collect { response ->
                    _customerVisitResponse.value = response
                }
            } catch (e: Exception) {
                Log.e("CustomerViewModel registerCustomerVisit Error", e.message.toString())
            }
        }
    }

    // 고객 특이사항 수정
    fun updateCustomerParticular(staffId: Long, memberId: Long, managementProfileUpdateNoteRequestDto: ManagementProfileUpdateNoteRequestDto) {
        viewModelScope.launch {
            try {
                customerApiRepository.updateCustomerParticular(staffId, memberId, managementProfileUpdateNoteRequestDto).collect { response ->
                    _customerParticularResponse.value = response
                }
            } catch (e: Exception) {
                Log.e("CustomerViewModel updateCustomerParticular Error", e.message.toString())
            }
        }
    }
}
