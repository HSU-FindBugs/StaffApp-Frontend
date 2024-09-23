package com.example.findbug.domain.model.response

import java.time.LocalTime

// 단일 벌레 기록 세부 정보 Response
data class BugRecordResponse(
    val bugName: String? = "",
    val bugDescription: String? = "",
    val cameraId: Long? = 0,
    val bugFindDate: String? = "",
    val bugFindTime: LocalTime? = null,
    val bugDetailDto: BugDetailResponse? = null,
    val bugSolutionDto: BugSolutionResponse? = null
)

// 알림 Response
data class BugDetectionAlertResponse(
    val bugName: String? = "",
    val bugDescription: String? = "",
    val cameraId: Long? = 0,
    val bugDetailDto: BugDetailResponse? = null,
    val bugSolutionDto: BugSolutionResponse? = null
)

// 발견된 해충 정보 Response
data class BugDetailResponse(
    val appearance: String? = "",
    val inhabitation: String? = "",
    val quarantine	: String? = "",
)

// 해충 방제 솔루션 Response
data class BugSolutionResponse(
    val firstSolution: String? = "",
    val secondSolution: String? = "",
    val thirdSolution	: String? = "",
    val fourSolution	: String? = "",
)