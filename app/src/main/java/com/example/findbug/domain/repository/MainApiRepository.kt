package com.example.findbug.domain.repository

import com.example.findbug.domain.model.response.MainPageResponse
import com.example.findbug.domain.model.response.SseEmitter
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MainApiRepository {
    suspend fun getMainPage(id: Long): Flow<Response<MainPageResponse>>
    suspend fun notificationConnect(staffId: Long): Flow<Response<SseEmitter>>
}