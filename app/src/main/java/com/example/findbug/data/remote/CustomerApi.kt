package com.example.findbug.data.remote

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
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CustomerApi {

    // 고객 관리 - 고객 찾기

    // 회원 정보 업데이트
    @PUT("/management/update")
    suspend fun updateCustomerInfo(
        @Body memberUpdateRequestDto: MemberUpdateRequestDto
    ): String

    // 고객 등록
    @POST("/management/register")
    suspend fun registerCustomer(
        @Body memberRegisterRequestDto: MemberRegisterRequestDto
    ): BaseResponse<ManagementPageSaveResponse>

    // 고객 목록 조회
    @GET("/management/{staff_id}/{page}")
    suspend fun getCustomerList(
        @Path("staff_id") staff_id: Int,
        @Path("page") page : Int
    ): BaseListResponse<ManagementPageResponse>

    // 회원 프로필 정보 확인
    @GET("/management/{member_id}")
    suspend fun getMemberProfile(
        @Path("member_id") member_id: Int
    ): BaseResponse<ManagementPageMemberDto>

    // 고객 정보 검색
    @GET("/management/search/{staff_id}/{member_name}")
    suspend fun customerInfoSearch(
        @Path("staff_id") staff_id: Int,
        @Path("member_name") member_name : String
    ): BaseListResponse<ManagementPageResponse>

    // 사용자 최신 검색 기록 조회
    @GET("/management/recent/{staff_id}")
    suspend fun getRecentCustomerSearchList(
        @Path("staff_id") staff_id: Int
    ): BaseListResponse<ManagementPageRecentSearchResponse>

    // 고객 관리 - 고객 정보 확인

    // 고객 프로필 조회
    @GET("/management/visit/{staff_id}/{member_id}")
    suspend fun getCustomerProfile(
        @Path("staff_id") staff_id: Int,
        @Path("member_id") member_id: Int
    ): BaseResponse<ManagementProfileResponse>

    // 고객 방문 등록
    @POST("/management/visit/{staff_id}/{member_id}")
    suspend fun registerCustomerVisit(
        @Path("staff_id") staff_id: Int,
        @Path("member_id") member_id: Int
    ): BaseResponse<ManagementProfileSaveResponse>

    // 고객 특이사항 수정
    @POST("/management/visit/{staff_id}/{member_id}/memo")
    suspend fun updateCustomerParticular (
        @Path("staff_id") staff_id: Int,
        @Path("member_id") member_id: Int,
        @Body managementProfileUpdateNoteRequestDto: ManagementProfileUpdateNoteRequestDto
    ): BaseResponse<ManagementProfileSaveResponse>

}