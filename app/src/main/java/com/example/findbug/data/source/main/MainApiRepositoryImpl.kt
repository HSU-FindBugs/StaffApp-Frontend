package com.example.findbug.data.source.main

import com.example.findbug.base.BaseResponse
import com.example.findbug.domain.model.response.MainPageResponse
import com.example.findbug.domain.model.response.SseEmitter
import com.example.findbug.domain.repository.MainApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainApiRepositoryImpl @Inject constructor(
    private val dataSource: MainApiDataSource
): MainApiRepository {

    override suspend fun getMainPage(id: Int): Flow<BaseResponse<MainPageResponse>>
    = dataSource.getMainPage(id)

    override suspend fun notificationConnect(staffId: Int): Flow<BaseResponse<SseEmitter>>
    = dataSource.notificationConnect(staffId)


}