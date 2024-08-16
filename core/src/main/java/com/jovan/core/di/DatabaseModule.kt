package com.jovan.core.di

import android.content.Context
import androidx.room.Room
import com.jovan.core.data.source.local.room.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): UserDatabase = Room.databaseBuilder(
        context.applicationContext,
        UserDatabase::class.java,
        "user_database"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideFavoriteDao(database: UserDatabase) = database.favoriteDao()

}