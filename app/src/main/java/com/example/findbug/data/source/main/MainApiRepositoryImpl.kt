package com.example.findbug.data.source.main

import com.example.findbug.domain.model.response.BugRecordResponse
import com.example.findbug.domain.model.response.DetectionHistory
import com.example.findbug.domain.model.response.MainPageResponse
import com.example.findbug.domain.model.response.ProfileResponse
import com.example.findbug.domain.model.response.SseEmitter
import com.example.findbug.domain.repository.MainApiRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MainApiRepositoryImpl @Inject constructor(
    private val dataSource: MainApiDataSource
): MainApiRepository {

    override suspend fun getMainPage(id: Long): Flow<Response<MainPageResponse>>
    = dataSource.getMainPage(id)

    override suspend fun notificationConnect(staffId: Long): Flow<Response<SseEmitter>>
    = dataSource.notificationConnect(staffId)

    override suspend fun getProfile(staffId: Long): Flow<Response<ProfileResponse>>
    = dataSource.getProfile(staffId)

    override suspend fun getBugRecord(detectionHistoryId: Long): Flow<Response<BugRecordResponse>>
    = dataSource.getBugRecord(detectionHistoryId)


    override suspend fun getBugRecordList(member_id: Long): Flow<Response<List<DetectionHistory>>>
    = dataSource.getBugRecordList(member_id)

}