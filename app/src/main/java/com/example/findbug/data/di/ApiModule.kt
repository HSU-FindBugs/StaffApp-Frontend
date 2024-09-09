package com.example.findbug.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

//    @Provides
//    @Singleton
//    fun provideFastApi(
//        @NetworkModule.ApiRetrofit retrofit: Retrofit
//    ): FastApi = retrofit.create(FastApi::class.java)

}