package com.example.findbug.data.source.main

import android.util.Log
import com.example.findbug.data.remote.MainApi
import com.example.findbug.domain.model.response.MainPageResponse
import com.example.findbug.domain.model.response.SseEmitter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MainApiDataSource @Inject constructor(
    private val mainApi: MainApi
) {

    fun getMainPage(id: Int): Flow<Response<MainPageResponse>> = flow {
        val result = mainApi.getMainPage(id)
        emit(result)
    }.catch { e ->
        Log.e("MainApiDataSource 에러", e.message.toString())
    }

    fun notificationConnect(staffId: Int): Flow<Response<SseEmitter>> = flow {
        val result = mainApi.notificationConnect(staffId)
        emit(result)
    }.catch { e ->
        Log.e("MainApiDataSource 에러", e.message.toString())
    }

}