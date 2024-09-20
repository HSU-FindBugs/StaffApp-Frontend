package com.example.findbug.domain.model.response

// 메인 페이지 조회
data class MainPageResponse(
    val staffProfileUrl: String? = "",
    val staffName: String? = "",
    val staffTerritory: String? = "",
    val countMemberManagedByStaff: Int? = 0,
    val bugName: String? = "",
    val bugDescription: String? = "",
    val bugStats: String? = "",
    val notificationDtoList: List<NotificationDto>
)

data class NotificationDto(
    val profileUrl: String? = "",
    val title: String? = "",
    val content: String? = "",
)

// sse-controller
data class SseEmitter(
    val timeout: String
)