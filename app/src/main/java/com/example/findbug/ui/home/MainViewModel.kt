package com.example.findbug.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findbug.base.BaseResponse
import com.example.findbug.domain.model.response.MainPageResponse
import com.example.findbug.domain.model.response.SseEmitter
import com.example.findbug.domain.repository.MainApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainApiRepository: MainApiRepository
): ViewModel() {

    private val _mainPageResponse =  MutableStateFlow<BaseResponse<MainPageResponse>>(BaseResponse())
    val mainPageResponse: MutableStateFlow<BaseResponse<MainPageResponse>> = _mainPageResponse

    private val _sseEmitter =  MutableStateFlow<BaseResponse<SseEmitter>>(BaseResponse())
    val sseEmitter: MutableStateFlow<BaseResponse<SseEmitter>> = _sseEmitter


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

}