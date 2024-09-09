package com.example.findbug.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

//    @Singleton
//    @Provides
//    fun provideMainApiRepository(mainApiDataSource: MainApiDataSource) : MainApiRepository =
//        MainRepositoryImpl(mainApiDataSource)

}