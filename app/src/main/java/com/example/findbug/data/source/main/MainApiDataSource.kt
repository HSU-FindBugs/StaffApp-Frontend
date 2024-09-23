package com.example.findbug.data.source.main

import android.util.Log
import com.example.findbug.data.remote.MainApi
import com.example.findbug.domain.model.response.BugRecordResponse
import com.example.findbug.domain.model.response.DetectionHistory
import com.example.findbug.domain.model.response.MainPageResponse
import com.example.findbug.domain.model.response.ProfileResponse
import com.example.findbug.domain.model.response.SseEmitter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MainApiDataSource @Inject constructor(
    private val mainApi: MainApi
) {

    fun getMainPage(id: Long): Flow<Response<MainPageResponse>> = flow {
        val result = mainApi.getMainPage(id)
        emit(result)
    }.catch { e ->
        Log.e("MainApiDataSource 에러", e.message.toString())
    }

    fun notificationConnect(staffId: Long): Flow<Response<SseEmitter>> = flow {
        val result = mainApi.notificationConnect(staffId)
        emit(result)
    }.catch { e ->
        Log.e("MainApiDataSource 에러", e.message.toString())
    }

    fun getProfile(staffId: Long):  Flow<Response<ProfileResponse>> = flow {
        val result = mainApi.getProfile(staffId)
        emit(result)
    }.catch { e ->
        Log.e("MainApiDataSource 에러", e.message.toString())
    }

    fun getBugRecord(detectionHistoryId: Long): Flow<Response<BugRecordResponse>>  = flow {
        val result = mainApi.getBugRecord(detectionHistoryId)
        emit(result)
    }.catch { e ->
        Log.e("MainApiDataSource 에러", e.message.toString())
    }

    fun getBugRecordList(member_id: Long): Flow<Response<List<DetectionHistory>>>   = flow {
        val result = mainApi.getBugRecordList(member_id)
        emit(result)
    }.catch { e ->
        Log.e("MainApiDataSource 에러", e.message.toString())
    }

}