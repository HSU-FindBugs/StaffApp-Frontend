package com.example.findbug.data.source.camera

import com.example.findbug.base.BaseListResponse
import com.example.findbug.base.BaseResponse
import com.example.findbug.domain.model.request.CameraRegisterRequestDto
import com.example.findbug.domain.model.response.CameraListResponse
import com.example.findbug.domain.model.response.CameraSaveResponse
import com.example.findbug.domain.repository.CameraApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CameraApiRepositoryImpl @Inject constructor(
    private val dataSource: CameraApiDataSource
): CameraApiRepository{

    override suspend fun addCamera(cameraRegisterRequestDto: CameraRegisterRequestDto): Flow<BaseResponse<CameraSaveResponse>>
    = dataSource.addCamera(cameraRegisterRequestDto)

    override suspend fun getCamera(member_id: Int): Flow<BaseListResponse<CameraListResponse>>
    = dataSource.getCamera(member_id)

}