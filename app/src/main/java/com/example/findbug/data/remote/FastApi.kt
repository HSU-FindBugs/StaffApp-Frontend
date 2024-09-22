package com.example.findbug.data.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface FastApi {

    // 실시간 영상 조회
    @Streaming
    @GET("video/{id}")
    suspend fun getLiveStream(
        @Path("id") id: Int
    ): Response<ResponseBody>

}