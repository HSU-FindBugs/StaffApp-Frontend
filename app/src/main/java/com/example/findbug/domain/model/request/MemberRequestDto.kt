package com.example.findbug.domain.model.request

// 회원 정보 업데이트 Request
data class MemberUpdateRequestDto(
    val staffId: Long? = 0,
    val memberId: Long? = 0,
    val name: String? = "",
    val email: String? = "",
    val phoneNumber: String? = "",
    val memberShip: Int? = 0,
    val address: Address
)

data class Address(
    val region_1depth: String? = "",
    val region_2depth: String? = "",
    val region_3depth: String? = "",
    val street_name: String? = "",
    val detail_address: String? = "",
)

// 고객 특이사항 수정 Request
data class ManagementProfileUpdateNoteRequestDto(
    val note: String? = ""
)