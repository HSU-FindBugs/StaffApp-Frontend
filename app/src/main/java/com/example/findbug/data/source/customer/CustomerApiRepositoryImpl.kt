package com.example.findbug.data.source.customer

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
import com.example.findbug.domain.repository.CustomerApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CustomerApiRepositoryImpl @Inject constructor(
    private val dataSource: CustomerApiDataSource
): CustomerApiRepository {

    override suspend fun updateCustomerInfo(memberUpdateRequestDto: MemberUpdateRequestDto): Flow<String>
    = dataSource.updateCustomerInfo(memberUpdateRequestDto)

    override suspend fun registerCustomer(memberRegisterRequestDto: MemberRegisterRequestDto): Flow<BaseResponse<ManagementPageSaveResponse>>
    = dataSource.registerCustomer(memberRegisterRequestDto)

    override suspend fun getCustomerList(
        staffId: Int,
        page: Int
    ): Flow<BaseListResponse<ManagementPageResponse>> = dataSource.getCustomerList(staffId, page)

    override suspend fun getMemberProfile(memberId: Int): Flow<BaseResponse<ManagementPageMemberDto>>
    = dataSource.getMemberProfile(memberId)

    override suspend fun customerInfoSearch(
        staffId: Int,
        memberName: String
    ): Flow<BaseListResponse<ManagementPageResponse>> = dataSource.customerInfoSearch(staffId, memberName)

    override suspend fun getRecentCustomerSearchList(staffId: Int): Flow<BaseListResponse<ManagementPageRecentSearchResponse>>
    = dataSource.getRecentCustomerSearchList(staffId)

    override suspend fun getCustomerProfile(
        staffId: Int,
        memberId: Int
    ): Flow<BaseResponse<ManagementProfileResponse>> = dataSource.getCustomerProfile(staffId, memberId)

    override suspend fun registerCustomerVisit(
        staffId: Int,
        memberId: Int
    ): Flow<BaseResponse<ManagementProfileSaveResponse>> = dataSource.registerCustomerVisit(staffId, memberId)

    override suspend fun updateCustomerParticular(
        staffId: Int,
        memberId: Int,
        managementProfileUpdateNoteRequestDto: ManagementProfileUpdateNoteRequestDto
    ): Flow<BaseResponse<ManagementProfileSaveResponse>> = dataSource.updateCustomerParticular(staffId, memberId, managementProfileUpdateNoteRequestDto)

}