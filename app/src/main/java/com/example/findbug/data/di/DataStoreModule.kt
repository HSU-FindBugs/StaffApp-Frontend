package com.example.findbug.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

//    @Singleton
//    @Provides
//    fun provideTokenManager(@ApplicationContext context: Context) : TokenManager {
//        return TokenManager(context)
//    }
}