package com.example.findbug.domain.repository

import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response

interface FastApiRepository {
    suspend fun getLiveStream(id: Int): Flow<Response<ResponseBody>>
}