package com.jovan.core.domain.usecase

import com.jovan.core.data.source.remote.network.ApiResponse
import com.jovan.core.domain.model.DetailUser
import com.jovan.core.domain.model.User
import com.jovan.core.domain.model.UserList
import kotlinx.coroutines.flow.Flow

interface GithubUseCase {
    fun getTheme(): Flow<Boolean>

    suspend fun setThemeSetting(isDarkModeActive: Boolean)

    //Local
    fun getAllFavoriteUser(): Flow<List<User>>

    fun insertFavoriteUser(userEntity: User)

    fun deleteFavoriteUser(userEntity: User)
    fun getFavoriteUser(id: Int): Flow<User?>

    //Remote
    suspend fun getSearchUser(query: String): Flow<ApiResponse<UserList>>

    suspend fun getUserDetail(username: String): DetailUser

    suspend fun getFollowers(username: String): List<User>

    suspend fun getFollowing(username: String): List<User>
}