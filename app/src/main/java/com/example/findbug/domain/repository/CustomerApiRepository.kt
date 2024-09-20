package com.example.findbug.domain.repository

import com.example.findbug.base.BaseListResponse
import com.example.findbug.base.BaseResponse
import com.example.findbug.domain.model.request.ManagementProfileUpdateNoteRequestDto
import com.example.findbug.domain.model.request.MemberRegisterRequestDto
import com.example.findbug.domain.model.request.MemberUpdateRequestDto
import com.example.findbug.domain.model.response.ManagementPageMemberDto
import com.example.findbug.domain.model.response.ManagementPageRecentSearchResponse
import com.example.findbug.domain.model.response.ManagementPageResponse
import com.example.findbug.domain.model.response.ManagementPageSaveResponse
import com.example.findbug.domain.model.response.ManagementProfileResponse
import com.example.findbug.domain.model.response.ManagementProfileSaveResponse
import kotlinx.coroutines.flow.Flow

interface CustomerApiRepository {

    // 회원 정보 업데이트
    suspend fun updateCustomerInfo(
        memberUpdateRequestDto: MemberUpdateRequestDto
    ): Flow<String>

    // 고객 등록
    suspend fun registerCustomer(
        memberRegisterRequestDto: MemberRegisterRequestDto
    ): Flow<BaseResponse<ManagementPageSaveResponse>>

    // 고객 목록 조회
    suspend fun getCustomerList(
        staffId: Int,
        page: Int
    ): Flow<BaseListResponse<ManagementPageResponse>>

    // 회원 프로필 정보 확인
    suspend fun getMemberProfile(
        memberId: Int
    ): Flow<BaseResponse<ManagementPageMemberDto>>

    // 고객 정보 검색
    suspend fun customerInfoSearch(
        staffId: Int,
        memberName: String
    ): Flow<BaseListResponse<ManagementPageResponse>>

    // 사용자 최신 검색 기록 조회
    suspend fun getRecentCustomerSearchList(
        staffId: Int
    ): Flow<BaseListResponse<ManagementPageRecentSearchResponse>>

    // 고객 프로필 조회
    suspend fun getCustomerProfile(
        staffId: Int,
        memberId: Int
    ): Flow<BaseResponse<ManagementProfileResponse>>

    // 고객 방문 등록
    suspend fun registerCustomerVisit(
        staffId: Int,
        memberId: Int
    ): Flow<BaseResponse<ManagementProfileSaveResponse>>

    // 고객 특이사항 수정
    suspend fun updateCustomerParticular(
        staffId: Int,
        memberId: Int,
        managementProfileUpdateNoteRequestDto: ManagementProfileUpdateNoteRequestDto
    ): Flow<BaseResponse<ManagementProfileSaveResponse>>
}
