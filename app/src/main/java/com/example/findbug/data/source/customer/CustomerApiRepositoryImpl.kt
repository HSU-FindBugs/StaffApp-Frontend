package com.example.findbug.data.source.customer

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
import retrofit2.Response
import javax.inject.Inject

class CustomerApiRepositoryImpl @Inject constructor(
    private val dataSource: CustomerApiDataSource
): CustomerApiRepository {

    override suspend fun updateCustomerInfo(memberUpdateRequestDto: MemberUpdateRequestDto): Flow<String>
    = dataSource.updateCustomerInfo(memberUpdateRequestDto)

    override suspend fun registerCustomer(memberRegisterRequestDto: MemberRegisterRequestDto): Flow<Response<ManagementPageSaveResponse>>
    = dataSource.registerCustomer(memberRegisterRequestDto)

    override suspend fun getCustomerList(
        staffId: Int,
        page: Int
    ): Flow<Response<ManagementPageResponse>> = dataSource.getCustomerList(staffId, page)

    override suspend fun getMemberProfile(memberId: Int): Flow<Response<ManagementPageMemberDto>>
    = dataSource.getMemberProfile(memberId)

    override suspend fun customerInfoSearch(
        staffId: Int,
        memberName: String
    ): Flow<Response<ManagementPageResponse>> = dataSource.customerInfoSearch(staffId, memberName)

    override suspend fun getRecentCustomerSearchList(staffId: Int): Flow<Response<ManagementPageRecentSearchResponse>>
    = dataSource.getRecentCustomerSearchList(staffId)

    override suspend fun getCustomerProfile(
        staffId: Int,
        memberId: Int
    ): Flow<Response<ManagementProfileResponse>> = dataSource.getCustomerProfile(staffId, memberId)

    override suspend fun registerCustomerVisit(
        staffId: Int,
        memberId: Int
    ): Flow<Response<ManagementProfileSaveResponse>> = dataSource.registerCustomerVisit(staffId, memberId)

    override suspend fun updateCustomerParticular(
        staffId: Int,
        memberId: Int,
        managementProfileUpdateNoteRequestDto: ManagementProfileUpdateNoteRequestDto
    ): Flow<Response<ManagementProfileSaveResponse>> = dataSource.updateCustomerParticular(staffId, memberId, managementProfileUpdateNoteRequestDto)

}