package com.example.findbug.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MainRetrofit

//    @Singleton
//    @Provides
//    fun provideAccessTokenInterceptor(tokenManager: TokenManager): AccessTokenInterceptor {
//        return AccessTokenInterceptor(tokenManager)
//    }

    @Singleton
    @Provides
    fun provideConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Singleton
    @Provides
    @Named("defaultOkHttpClient")
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @MainRetrofit
    @Singleton
    @Provides
    fun provideMainRetrofit(
        @Named("defaultMainApiClient") okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .baseUrl("")
            .build()
    }

}