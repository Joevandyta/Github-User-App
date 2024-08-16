package com.jovan.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jovan.core.data.source.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}

