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

    private val _bugDetectionAlertResponse =  MutableStateFlow(BugDetectionAlertResponse())
    val bugDetectionAlertResponse: MutableStateFlow<BugDetectionAlertResponse> = _bugDetectionAlertResponse

    private val _profileResponse =  MutableStateFlow(Response.success(ProfileResponse()))
    val profileResponse: MutableStateFlow<Response<ProfileResponse>> = _profileResponse

    private val _bugRecordResponse =  MutableStateFlow(Response.success(BugRecordResponse()))
    val bugRecordResponse: MutableStateFlow<Response<BugRecordResponse>> = _bugRecordResponse

    private val _detectionHistory = MutableStateFlow<Response<List<DetectionHistory>>>(Response.success(emptyList()))
    val detectionHistory: MutableStateFlow<Response<List<DetectionHistory>>> = _detectionHistory

    // 추가된 프로퍼티
    private val _name = MutableStateFlow<String?>(null)
    val name: MutableStateFlow<String?> = _name

    private val _fullAddress = MutableStateFlow<String?>(null)
    val fullAddress: MutableStateFlow<String?> = _fullAddress

    fun addBugDetectionAlert(bugDetectionAlertResponse: BugDetectionAlertResponse) {
        _bugDetectionAlertResponse.value = bugDetectionAlertResponse
        _name.value = bugDetectionAlertResponse.name ?: ""

        val address = bugDetectionAlertResponse.address
        val formattedAddress = buildString {
            if (!address?.region_1depth.isNullOrEmpty()) append("${address?.region_1depth} ")
            if (!address?.region_2depth.isNullOrEmpty()) append("${address?.region_2depth} ")
            if (!address?.region_3depth.isNullOrEmpty()) append("${address?.region_3depth} ")
            if (!address?.street_name.isNullOrEmpty()) append("${address?.street_name} ")
            if (!address?.detail_address.isNullOrEmpty()) append("${address?.detail_address}")
        }.trim()

        _fullAddress.value = formattedAddress
    }

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