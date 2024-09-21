package com.example.findbug.ui.customer_manage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findbug.domain.model.request.CameraRegisterRequestDto
import com.example.findbug.domain.model.response.CameraListResponse
import com.example.findbug.domain.model.response.CameraSaveResponse
import com.example.findbug.domain.repository.CameraApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val cameraApiRepository: CameraApiRepository
): ViewModel() {

    private val _cameraSaveResponse =  MutableStateFlow(Response.success(CameraSaveResponse()))
    val cameraSaveResponse: MutableStateFlow<Response<CameraSaveResponse>> = _cameraSaveResponse

    private val _cameraListResponse =  MutableStateFlow(Response.success(CameraListResponse()))
    val cameraListResponse: MutableStateFlow<Response<CameraListResponse>> = _cameraListResponse

    fun addCamera(cameraRegisterRequestDto: CameraRegisterRequestDto) {
        viewModelScope.launch {
            try {
                cameraApiRepository.addCamera(cameraRegisterRequestDto).collect() {
                    _cameraSaveResponse.value = it
                }
            } catch (e:Exception) {
                Log.e("TodayViewModel getMainPage Error", e.message.toString())
            }
        }
    }

    fun getCamera(member_id: Int) {
        viewModelScope.launch {
            try {
                cameraApiRepository.getCamera(member_id).collect() {
                    _cameraListResponse.value = it
                }
            } catch (e:Exception) {
                Log.e("TodayViewModel notificationConnect Error", e.message.toString())
            }
        }
    }

}