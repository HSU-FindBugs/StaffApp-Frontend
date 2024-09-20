package com.example.findbug.domain.repository

import com.example.findbug.base.BaseResponse
import com.example.findbug.domain.model.response.MainPageResponse
import com.example.findbug.domain.model.response.SseEmitter
import kotlinx.coroutines.flow.Flow

interface MainApiRepository {
    suspend fun getMainPage(id: Int): Flow<BaseResponse<MainPageResponse>>
    suspend fun notificationConnect(staffId: Int): Flow<BaseResponse<SseEmitter>>
}