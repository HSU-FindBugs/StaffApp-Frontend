package com.example.findbug.domain.repository

import com.example.findbug.domain.model.request.CameraRegisterRequestDto
import com.example.findbug.domain.model.response.CameraListResponse
import com.example.findbug.domain.model.response.CameraSaveResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CameraApiRepository {
    suspend fun addCamera(cameraRegisterRequestDto: CameraRegisterRequestDto): Flow<Response<CameraSaveResponse>>
    suspend fun getCamera(member_id: Int): Flow<Response<CameraListResponse>>
}