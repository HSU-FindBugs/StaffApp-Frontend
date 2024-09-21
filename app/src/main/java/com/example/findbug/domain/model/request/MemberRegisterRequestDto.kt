package com.example.findbug.domain.model.request

// 고객 등록 Request
data class MemberRegisterRequestDto(
    val staffId: Int? = 0,
    val name: String? = "",
    val email: String? = "",
    val phoneNumber: String? = "",
    val memberShip: Int? = 0,
    val address: Address
)
