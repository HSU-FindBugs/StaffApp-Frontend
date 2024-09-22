package com.example.findbug.data.source.camera

import com.example.findbug.domain.model.request.CameraRegisterRequestDto
import com.example.findbug.domain.model.response.CameraListResponse
import com.example.findbug.domain.model.response.CameraSaveResponse
import com.example.findbug.domain.repository.CameraApiRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class CameraApiRepositoryImpl @Inject constructor(
    private val dataSource: CameraApiDataSource
): CameraApiRepository{

    override suspend fun addCamera(cameraRegisterRequestDto: CameraRegisterRequestDto): Flow<Response<CameraSaveResponse>>
    = dataSource.addCamera(cameraRegisterRequestDto)

    override suspend fun getCamera(member_id: Long): Flow<Response<CameraListResponse>>
    = dataSource.getCamera(member_id)

}