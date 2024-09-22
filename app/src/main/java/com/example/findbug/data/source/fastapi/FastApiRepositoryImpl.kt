package com.example.findbug.data.source.fastapi

import com.example.findbug.data.source.main.MainApiDataSource
import com.example.findbug.domain.repository.FastApiRepository
import com.example.findbug.domain.repository.MainApiRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class FastApiRepositoryImpl @Inject constructor(
    private val dataSource: FastApiDataSource
): FastApiRepository {

    override suspend fun getLiveStream(id: Int): Flow<Response<ResponseBody>> =  // 추가된 부분
        dataSource.getLiveStream(id)

}