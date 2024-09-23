package com.example.findbug.domain.repository

import com.example.findbug.domain.model.response.BugRecordResponse
import com.example.findbug.domain.model.response.DetectionHistory
import com.example.findbug.domain.model.response.MainPageResponse
import com.example.findbug.domain.model.response.ProfileResponse
import com.example.findbug.domain.model.response.SseEmitter
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MainApiRepository {
    suspend fun getMainPage(id: Long): Flow<Response<MainPageResponse>>
    suspend fun notificationConnect(staffId: Long): Flow<Response<SseEmitter>>
    suspend fun getProfile(staffId: Long): Flow<Response<ProfileResponse>>
    suspend fun getBugRecord(detectionHistoryId: Long): Flow<Response<BugRecordResponse>>
    suspend fun getBugRecordList(member_id: Long): Flow<Response<List<DetectionHistory>>>
}