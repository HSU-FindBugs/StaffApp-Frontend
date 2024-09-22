package com.example.findbug.data.remote

import com.example.findbug.domain.model.response.MainPageResponse
import com.example.findbug.domain.model.response.SseEmitter
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MainApi {

    // 메인 페이지 조회
    @GET("/api/main/{id}")
    suspend fun getMainPage(
        @Path("id") id: Long
    ): Response<MainPageResponse>

    // sse-controller
    @GET("/detection-events/connect/{staffId}")
    suspend fun notificationConnect(
        @Path("staffId") staffId: Long
    ): Response<SseEmitter>

}