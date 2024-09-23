package com.example.findbug.data.remote

import com.example.findbug.domain.model.response.BugRecordResponse
import com.example.findbug.domain.model.response.DetectionHistory
import com.example.findbug.domain.model.response.MainPageResponse
import com.example.findbug.domain.model.response.ProfileResponse
import com.example.findbug.domain.model.response.SseEmitter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {

    // 메인 페이지 조회
    @GET("/api/main/{id}")
    suspend fun getMainPage(
        @Path("id") id: Long
    ): Response<MainPageResponse>

    // sse-controller (알림)
    @GET("/detection-events/connect/{staffId}")
    suspend fun notificationConnect(
        @Path("staffId") staffId: Long
    ): Response<SseEmitter>

    // 프로필 조회
    @GET("/profile/{staffId}")
    suspend fun getProfile(
        @Path("staffId") staffId: Long
    ): Response<ProfileResponse>

    // 단일 벌레 기록 세부정보 조회
    @GET("/bug-record/detection-history/{detectionHistoryId}")
    suspend fun getBugRecord(
        @Path("detectionHistoryId") detectionHistoryId: Long
    ) : Response<BugRecordResponse>

    //  벌레 탐지 이력 목록 조회
    @GET("/bug-record/member/{member_id}/detection-history")
    suspend fun getBugRecordList(
        @Path("member_id") member_id: Long
    ): Response<List<DetectionHistory>>

}