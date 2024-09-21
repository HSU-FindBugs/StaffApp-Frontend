package com.example.findbug.data.source.camera

import android.util.Log
import com.example.findbug.data.remote.CameraApi
import com.example.findbug.domain.model.request.CameraRegisterRequestDto
import com.example.findbug.domain.model.response.CameraListResponse
import com.example.findbug.domain.model.response.CameraSaveResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class CameraApiDataSource @Inject constructor(
    private val cameraApi: CameraApi
) {

    fun addCamera(cameraRegisterRequestDto: CameraRegisterRequestDto): Flow<Response<CameraSaveResponse>> = flow {
        val result = cameraApi.addCamera(cameraRegisterRequestDto)
        emit(result)
    }.catch { e ->
        Log.e("CameraApiDataSource 에러", e.message.toString())
    }

    fun getCamera(member_id: Int): Flow<Response<CameraListResponse>> = flow {
        val result = cameraApi.getCamera(member_id)
        emit(result)
    }.catch { e ->
        Log.e("CameraApiDataSource 에러", e.message.toString())
    }

}