package com.example.findbug.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findbug.domain.model.response.MainPageResponse
import com.example.findbug.domain.model.response.ManagementPageSaveResponse
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

//    private val _liveStreamResponse = MutableStateFlow<Response<ResponseBody>?>(null)
//    val liveStreamResponse: MutableStateFlow<Response<ResponseBody>?> = _liveStreamResponse


    fun getMainPage(id: Int) {
        viewModelScope.launch {
            try {
                mainApiRepository.getMainPage(id).collect() {
                    _mainPageResponse.value = it
                }
            } catch (e:Exception) {
                Log.e("TodayViewModel getMainPage Error", e.message.toString())
            }
        }
    }

    fun notificationConnect(staffId: Int) {
        viewModelScope.launch {
            try {
                mainApiRepository.notificationConnect(staffId).collect() {
                    _sseEmitter.value = it
                }
            } catch (e:Exception) {
                Log.e("TodayViewModel notificationConnect Error", e.message.toString())
            }
        }
    }

//    fun getLiveStream(id: Int) {
//        viewModelScope.launch {
//            try {
//                fastApiRepository.getLiveStream(id).collect { response ->
//                    _liveStreamResponse.value = response
//                    Log.d("ㄹㄹㄹㄹ", "ㄹㄹㄹㄹ: $_liveStreamResponse")
//                }
//            } catch (e: Exception) {
//                Log.e("MainViewModel getLiveStream Error", e.message.toString())
//            }
//        }
//    }

}