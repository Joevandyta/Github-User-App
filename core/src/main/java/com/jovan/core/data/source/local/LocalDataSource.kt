package com.jovan.core.data.source.local

import com.jovan.core.data.source.local.entity.UserEntity
import com.jovan.core.data.source.local.room.FavoriteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val favoriteDao: FavoriteDao){

    fun getAllFavoriteUser(): Flow<List<UserEntity>> = favoriteDao.getAllFavorite()

    suspend fun insert(userEntity: UserEntity) {
        favoriteDao.insert(userEntity)
    }

    suspend fun delete(userEntity: UserEntity) {
        favoriteDao.delete(userEntity)
    }

    fun getFavoriteUser(id: Int): Flow<UserEntity?> {
        return favoriteDao.getFavoriteUser(id)
    }
}