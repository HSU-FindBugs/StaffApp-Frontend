package com.example.findbug.data.remote

import com.example.findbug.base.BaseResponse
import com.example.findbug.domain.model.request.CameraRegisterRequestDto
import com.example.findbug.domain.model.response.CameraSaveResponse
import com.example.findbug.domain.model.response.SseEmitter
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MainApi {

    // 메인 페이지 조회
    @POST("/camera")
    suspend fun addCamera(
        @Body cameraRegisterRequestDto: CameraRegisterRequestDto
    ): BaseResponse<CameraSaveResponse>

    // sse-controller
    @GET("/detection-events/connect/{staffId}")
    suspend fun notificationConnect(
        @Path("staffId") staffId: Int
    ): BaseResponse<SseEmitter>

}