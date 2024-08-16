package com.jovan.core.domain.usecase

import com.jovan.core.data.source.remote.network.ApiResponse
import com.jovan.core.domain.model.DetailUser
import com.jovan.core.domain.model.User
import com.jovan.core.domain.model.UserList
import com.jovan.core.domain.repository.IGithubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubInteractor @Inject constructor(private val githubRepository: IGithubRepository): GithubUseCase {
    override fun getTheme(): Flow<Boolean> =
        githubRepository.getTheme()


    override suspend fun setThemeSetting(isDarkModeActive: Boolean) =
        githubRepository.setThemeSetting(isDarkModeActive)


    override fun getAllFavoriteUser(): Flow<List<User>> =
        githubRepository.getAllFavoriteUser()


    override fun insertFavoriteUser(userEntity: User) =
        githubRepository.insertFavoriteUser(userEntity)


    override fun deleteFavoriteUser(userEntity: User) =
        githubRepository.deleteFavoriteUser(userEntity)


    override fun getFavoriteUser(id: Int): Flow<User?> =
        githubRepository.getFavoriteUser(id)


    override suspend fun getSearchUser(query: String): Flow<ApiResponse<UserList>> =
        githubRepository.getSearchUser(query)


    override suspend fun getUserDetail(username: String): DetailUser =
        githubRepository.getUserDetail(username)


    override suspend fun getFollowers(username: String): List<User> =
        githubRepository.getFollowers(username)


    override suspend fun getFollowing(username: String): List<User> =
        githubRepository.getFollowing(username)

}