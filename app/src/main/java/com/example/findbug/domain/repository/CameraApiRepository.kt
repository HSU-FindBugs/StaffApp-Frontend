package com.example.findbug.domain.repository

import com.example.findbug.base.BaseListResponse
import com.example.findbug.base.BaseResponse
import com.example.findbug.domain.model.request.CameraRegisterRequestDto
import com.example.findbug.domain.model.response.CameraListResponse
import com.example.findbug.domain.model.response.CameraSaveResponse
import kotlinx.coroutines.flow.Flow

interface CameraApiRepository {
    suspend fun addCamera(cameraRegisterRequestDto: CameraRegisterRequestDto): Flow<BaseResponse<CameraSaveResponse>>
    suspend fun getCamera(member_id: Int): Flow<BaseListResponse<CameraListResponse>>
}