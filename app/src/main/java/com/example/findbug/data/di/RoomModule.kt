package com.example.findbug.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

//    @Provides
//    @Singleton
//    fun provideRoomDatabase(@ApplicationContext context: Context) : AppDataBase {
//        return Room.databaseBuilder(
//            context, AppDataBase::class.java, "main_room_db"
//        ).build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideUserDao(database: AppDatabase): UserDao {
//        return database.userDao()
//    }

}