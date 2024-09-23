package com.example.findbug.data.source.customer

import android.util.Log
import com.example.findbug.data.remote.CustomerApi
import com.example.findbug.domain.model.request.ManagementProfileUpdateNoteRequestDto
import com.example.findbug.domain.model.request.MemberRegisterRequestDto
import com.example.findbug.domain.model.request.MemberUpdateRequestDto
import com.example.findbug.domain.model.response.DetectionHistoryResponse
import com.example.findbug.domain.model.response.ManagementPageMemberDto
import com.example.findbug.domain.model.response.ManagementPageRecentSearchResponse
import com.example.findbug.domain.model.response.ManagementPageResponse
import com.example.findbug.domain.model.response.ManagementPageSaveResponse
import com.example.findbug.domain.model.response.ManagementProfileResponse
import com.example.findbug.domain.model.response.ManagementProfileSaveResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class CustomerApiDataSource @Inject constructor(
    private val customerApi: CustomerApi
) {

    fun updateCustomerInfo(memberUpdateRequestDto: MemberUpdateRequestDto): Flow<Response<String>> = flow {
        val result = customerApi.updateCustomerInfo(memberUpdateRequestDto)
        emit(result)
    }.catch { e ->
        Log.e("CustomerApiDataSource 에러", e.message.toString())
    }

    fun registerCustomer(memberRegisterRequestDto: MemberRegisterRequestDto): Flow<Response<ManagementPageSaveResponse>> =
        flow {
            val result = customerApi.registerCustomer(memberRegisterRequestDto)
            emit(result)
        }.catch { e ->
            Log.e("CustomerApiDataSource 에러", e.message.toString())
        }

    // 고객 목록 조회
    fun getCustomerList(staffId: Long, page: Int): Flow<Response<ManagementPageResponse>> =
        flow {
            val result = customerApi.getCustomerList(staffId, page)
            emit(result)
        }.catch { e ->
            Log.e("CustomerApiDataSource 에러", e.message.toString())
        }

    // 회원 프로필 정보 확인
    fun getMemberProfile(memberId: Long): Flow<Response<ManagementPageMemberDto>> = flow {
        val result = customerApi.getMemberProfile(memberId)
        emit(result)
    }.catch { e ->
        Log.e("CustomerApiDataSource 에러", e.message.toString())
    }

    // 고객 정보 검색
    fun customerInfoSearch(
        staffId: Long,
        memberName: String
    ): Flow<Response<ManagementPageResponse>> = flow {
        val result = customerApi.customerInfoSearch(staffId, memberName)
        emit(result)
    }.catch { e ->
        Log.e("CustomerApiDataSource 에러", e.message.toString())
    }

    // 사용자 최신 검색 기록 조회
    fun getRecentCustomerSearchList(staffId: Long): Flow<Response<ManagementPageRecentSearchResponse>> =
        flow {
            val result = customerApi.getRecentCustomerSearchList(staffId)
            emit(result)
        }.catch { e ->
            Log.e("CustomerApiDataSource 에러", e.message.toString())
        }

    // 고객 프로필 조회
    fun getCustomerProfile(
        staffId: Long,
        memberId: Long
    ): Flow<Response<ManagementProfileResponse>> = flow {
        val result = customerApi.getCustomerProfile(staffId, memberId)
        emit(result)
    }.catch { e ->
        Log.e("CustomerApiDataSource 에러", e.message.toString())
    }

    // 고객 방문 등록
    fun registerCustomerVisit(
        staffId: Long,
        memberId: Long
    ): Flow<Response<ManagementProfileSaveResponse>> = flow {
        val result = customerApi.registerCustomerVisit(staffId, memberId)
        emit(result)
    }.catch { e ->
        Log.e("CustomerApiDataSource 에러", e.message.toString())
    }

    // 고객 특이사항 수정
    fun updateCustomerParticular(
        staffId: Long,
        memberId: Long,
        managementProfileUpdateNoteRequestDto: ManagementProfileUpdateNoteRequestDto
    ): Flow<Response<ManagementProfileSaveResponse>> = flow {
        val result = customerApi.updateCustomerParticular(
            staffId,
            memberId,
            managementProfileUpdateNoteRequestDto
        )
        emit(result)
    }.catch { e ->
        Log.e("CustomerApiDataSource 에러", e.message.toString())
    }

    // 고객 특이사항 수정
    fun getDetectedVideoList(
        staffId: Long,
        memberId: Long,
    ): Flow<Response<DetectionHistoryResponse>> = flow {
        val result = customerApi.getDetectedVideoList(
            staffId,
            memberId
        )
        emit(result)
    }.catch { e ->
        Log.e("CustomerApiDataSource 에러", e.message.toString())
    }

}