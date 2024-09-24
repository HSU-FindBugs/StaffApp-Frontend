package com.example.findbug.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findbug.domain.model.response.BugDetectionAlertResponse
import com.example.findbug.domain.model.response.BugRecordResponse
import com.example.findbug.domain.model.response.DetectionHistory
import com.example.findbug.domain.model.response.MainPageResponse
import com.example.findbug.domain.model.response.ManagementPageSaveResponse
import com.example.findbug.domain.model.response.ProfileResponse
import com.example.findbug.domain.model.response.SseEmitter
import com.example.findbug.domain.repository.FastApiRepository
import com.example.findbug.domain.repository.MainApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainApiRepository: MainApiRepository,
    private val fastApiRepository: FastApiRepository
): ViewModel() {

    private val _mainPageResponse =  MutableStateFlow(Response.success(MainPageResponse()))
    val mainPageResponse: MutableStateFlow<Response<MainPageResponse>> = _mainPageResponse

    private val _sseEmitter =  MutableStateFlow(Response.success(SseEmitter()))
    val sseEmitter: MutableStateFlow<Response<SseEmitter>> = _sseEmitter

    private val _bugDetectionAlertResponse =  MutableStateFlow(BugDetectionAlertResponse())
    val bugDetectionAlertResponse: MutableStateFlow<BugDetectionAlertResponse> = _bugDetectionAlertResponse

    private val _profileResponse =  MutableStateFlow(Response.success(ProfileResponse()))
    val profileResponse: MutableStateFlow<Response<ProfileResponse>> = _profileResponse

    private val _bugRecordResponse =  MutableStateFlow(Response.success(BugRecordResponse()))
    val bugRecordResponse: MutableStateFlow<Response<BugRecordResponse>> = _bugRecordResponse

    private val _detectionHistory = MutableStateFlow<Response<List<DetectionHistory>>>(Response.success(emptyList()))
    val detectionHistory: MutableStateFlow<Response<List<DetectionHistory>>> = _detectionHistory

    fun getMainPage(id: Long) {
        viewModelScope.launch {
            try {
                mainApiRepository.getMainPage(id).collect() {
                    _mainPageResponse.value = it
                }
            } catch (e:Exception) {
                Log.e("MainViewModel getMainPage Error", e.message.toString())
            }
        }
    }

    private var isNotificationConnected = false

    fun notificationConnect(staffId: Long) {
        // SSE 연결이 이미 설정되지 않았다면
        if (!isNotificationConnected) {
            isNotificationConnected = true  // 플래그를 설정하여 재연결 방지
            viewModelScope.launch {
                try {
                    // SSE 연결 시작
                    mainApiRepository.notificationConnect(staffId).collect { sseEvent ->
                        _sseEmitter.value = sseEvent  // 이벤트 수신 시 LiveData에 반영
                    }
                } catch (e: Exception) {
                    Log.e("MainViewModel notificationConnect Error", e.message.toString())
                    // 에러 발생 시 플래그를 다시 false로 설정하여 재시도 가능하게
                    isNotificationConnected = false
                }
            }
        } else {
            Log.d("MainViewModel", "SSE connection already established.")
        }
    }

    fun addBugDetectionAlert(bugDetectionAlertResponse: BugDetectionAlertResponse) {
        _bugDetectionAlertResponse.value = bugDetectionAlertResponse
    }

    fun getProfile(staffId: Long) {
        viewModelScope.launch {
            try {
                mainApiRepository.getProfile(staffId).collect() {
                    _profileResponse.value = it
                }
            } catch (e:Exception) {
                Log.e("MainViewModel getProfile Error", e.message.toString())
            }
        }
    }

    fun getBugRecord(detectionHistoryId: Long) {
        viewModelScope.launch {
            try {
                mainApiRepository.getBugRecord(detectionHistoryId).collect() {
                    _bugRecordResponse.value = it
                }
            } catch (e:Exception) {
                Log.e("MainViewModel getBugRecord Error", e.message.toString())
            }
        }
    }

    fun getBugRecordList(member_id: Long) {
        viewModelScope.launch {
            try {
                mainApiRepository.getBugRecordList(member_id).collect() {
                    _detectionHistory.value = it
                }
            } catch (e:Exception) {
                Log.e("MainViewModel getBugRecordList Error", e.message.toString())
            }
        }
    }

}