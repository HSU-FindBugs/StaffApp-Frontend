package com.example.findbug.data.remote

import com.example.findbug.domain.model.request.CameraRegisterRequestDto
import com.example.findbug.domain.model.response.CameraListResponse
import com.example.findbug.domain.model.response.CameraSaveResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CameraApi {

    // 카메라 등록
    @POST("/camera")
    suspend fun addCamera(
        @Body cameraRegisterRequestDto: CameraRegisterRequestDto
    ): Response<CameraSaveResponse>

    // 카메라 조회
    @GET("/camera/{member_id}")
    suspend fun getCamera(
        @Path("member_id") member_id: Int
    ) : Response<CameraListResponse>

}