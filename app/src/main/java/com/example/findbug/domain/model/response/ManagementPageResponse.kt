package com.example.findbug.domain.model.response

import com.example.findbug.domain.model.request.Address

// 사용자 최신 검색 기록 조회 Response
data class ManagementPageRecentSearchResponse(
    val recentSearchResults: List<String>? = null
)

// 고객 정보 조회 Response (고객 목록 조회, 고객 정보 검색)
data class ManagementPageResponse(
    val managementPageMemberDtoList: List<ManagementPageMemberDto>?,
    val isSearched: Boolean? = false,
    val searched: Boolean? = false,
)

// 프로필 정보 반환 Response
data class ManagementPageMemberDto(
    val id: Long? =0,
    val name: String? ="",
    val address: Address? = null,
    val recentVisit: String? = "",
    val phoneNumber: String? = ""
)

// 고객 등록 Response
data class ManagementPageSaveResponse(
    val checked: Boolean? = false
)

// 고객 정보 확인 Response (고객 프로필 조회)
data class ManagementProfileResponse(
    val managementProfilePageMemberDto: ManagementProfilePageMemberDto? = null,
    val managementProfilePageVisitDto: ManagementProfilePageVisitDto? = null
)

data class ManagementProfilePageMemberDto(
    val id: Long? = 0,
    val profileUrl: String? ="",
    val name: String? ="",
    val address: Address,
    val remainingDays: String? = "",
    val visitStatus: String? = "",
    val memo: String? = ""
)

data class ManagementProfilePageVisitDto(
    val visitId: Long? =0,
    val title: String? = "",
    val visitedAt: String? ="",
    val visitComment: String? ="",
    val detectedImgUrl: String? = ""
)

// 고객 방문 등록, 고객 특이사항 수정 Response
data class ManagementProfileSaveResponse(
    val isSaved: Boolean? = false,
    val saved: Boolean? = false
)