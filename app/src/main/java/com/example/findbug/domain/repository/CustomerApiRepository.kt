package com.example.findbug.domain.repository

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
import retrofit2.Response

interface CustomerApiRepository {

    // 회원 정보 업데이트
    suspend fun updateCustomerInfo(
        memberUpdateRequestDto: MemberUpdateRequestDto
    ): Flow<Response<String>>

    // 고객 등록
    suspend fun registerCustomer(
        memberRegisterRequestDto: MemberRegisterRequestDto
    ): Flow<Response<ManagementPageSaveResponse>>

    // 고객 목록 조회
    suspend fun getCustomerList(
        staffId: Long,
        page: Int
    ): Flow<Response<ManagementPageResponse>>

    // 회원 프로필 정보 확인
    suspend fun getMemberProfile(
        memberId: Long
    ): Flow<Response<ManagementPageMemberDto>>

    // 고객 정보 검색
    suspend fun customerInfoSearch(
        staffId: Long,
        memberName: String
    ): Flow<Response<ManagementPageResponse>>

    // 사용자 최신 검색 기록 조회
    suspend fun getRecentCustomerSearchList(
        staffId: Long
    ): Flow<Response<ManagementPageRecentSearchResponse>>

    // 고객 프로필 조회
    suspend fun getCustomerProfile(
        staffId: Long,
        memberId: Long
    ): Flow<Response<ManagementProfileResponse>>

    // 고객 방문 등록
    suspend fun registerCustomerVisit(
        staffId: Long,
        memberId: Long
    ): Flow<Response<ManagementProfileSaveResponse>>

    // 고객 특이사항 수정
    suspend fun updateCustomerParticular(
        staffId: Long,
        memberId: Long,
        managementProfileUpdateNoteRequestDto: ManagementProfileUpdateNoteRequestDto
    ): Flow<Response<ManagementProfileSaveResponse>>

    // 감지 영상 목록 조회
    suspend fun getDetectedVideoList(
        staffId: Long,
        memberId: Long
    ) : Flow<Response<DetectionHistoryResponse>>

}
