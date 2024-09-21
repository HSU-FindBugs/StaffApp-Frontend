package com.example.findbug.domain.model.response

// 카메라 조회 Response
data class CameraListResponse(
    val cameraDtoList : List<CameraDto>? = null
)

data class CameraDto(
    val id: Int? = 0,
    val serialId: String? = "",
    val name: String? =""
)

// 카메라 등록 Response
data class CameraSaveResponse(
    val saved: Boolean? = false
)