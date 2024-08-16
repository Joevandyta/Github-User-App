package com.jovan.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jovan.core.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userEntity: UserEntity)

    @Delete
    suspend fun delete(userEntity: UserEntity)

    @Query("SELECT * from userentity")
    fun getAllFavorite(): Flow<List<UserEntity>>

    @Query("SELECT * FROM userentity WHERE id = :id")
    fun getFavoriteUser(id: Int): Flow<UserEntity>
}