package com.example.findbug.data.di

import com.example.findbug.data.remote.CameraApi
import com.example.findbug.data.remote.CustomerApi
import com.example.findbug.data.remote.MainApi
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

    @Provides
    @Singleton
    fun provideMainApi(
        @NetworkModule.MainRetrofit retrofit: Retrofit
    ): MainApi = retrofit.create(MainApi::class.java)

    @Provides
    @Singleton
    fun provideCameraApi(
        @NetworkModule.MainRetrofit retrofit: Retrofit
    ): CameraApi = retrofit.create(CameraApi::class.java)

    @Provides
    @Singleton
    fun provideCustomerApi(
        @NetworkModule.MainRetrofit retrofit: Retrofit
    ): CustomerApi = retrofit.create(CustomerApi::class.java)

}