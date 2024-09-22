package com.example.findbug.data.di

import com.example.findbug.data.source.camera.CameraApiDataSource
import com.example.findbug.data.source.camera.CameraApiRepositoryImpl
import com.example.findbug.data.source.customer.CustomerApiDataSource
import com.example.findbug.data.source.customer.CustomerApiRepositoryImpl
import com.example.findbug.data.source.fastapi.FastApiDataSource
import com.example.findbug.data.source.fastapi.FastApiRepositoryImpl
import com.example.findbug.data.source.main.MainApiDataSource
import com.example.findbug.data.source.main.MainApiRepositoryImpl
import com.example.findbug.domain.repository.CameraApiRepository
import com.example.findbug.domain.repository.CustomerApiRepository
import com.example.findbug.domain.repository.FastApiRepository
import com.example.findbug.domain.repository.MainApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideFastApiRepository(fastApiDataSource: FastApiDataSource) : FastApiRepository =
        FastApiRepositoryImpl(fastApiDataSource)

    @Singleton
    @Provides
    fun provideMainApiRepository(mainApiDataSource: MainApiDataSource) : MainApiRepository =
        MainApiRepositoryImpl(mainApiDataSource)

    @Singleton
    @Provides
    fun provideCameraApiRepository(cameraApiDataSource: CameraApiDataSource) : CameraApiRepository =
        CameraApiRepositoryImpl(cameraApiDataSource)

    @Singleton
    @Provides
    fun provideCustomerApiRepository(customerApiDataSource: CustomerApiDataSource) : CustomerApiRepository =
        CustomerApiRepositoryImpl(customerApiDataSource)

}