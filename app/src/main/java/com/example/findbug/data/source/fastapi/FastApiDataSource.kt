package com.example.findbug.data.source.fastapi

import android.util.Log
import com.example.findbug.data.remote.FastApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class FastApiDataSource @Inject constructor(
    private val fastApi: FastApi
) {

    fun getLiveStream(id: Int): Flow<Response<ResponseBody>> = flow {
        val result = fastApi.getLiveStream(id)
        emit(result)
    }.catch { e ->
        Log.e("FastApiDataSource 에러", e.message.toString())
    }

}