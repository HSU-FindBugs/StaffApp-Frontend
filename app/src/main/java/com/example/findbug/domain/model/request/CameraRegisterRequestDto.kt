package com.example.findbug.domain.model.request

// 카메라 등록
data class CameraRegisterRequestDto(
    val memberId: Int? = 0,
    val cameraName: String? = "",
    val cameraSerialId: String? = ""
)
