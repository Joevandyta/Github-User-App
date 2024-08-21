package com.jovan.core.data

import com.jovan.core.data.source.local.LocalDataSource
import com.jovan.core.data.source.local.datastore.SettingPreferences
import com.jovan.core.data.source.remote.RemoteDataSource
import com.jovan.core.data.source.remote.network.ApiResponse
import com.jovan.core.domain.model.DetailUser
import com.jovan.core.domain.model.User
import com.jovan.core.domain.model.UserList
import com.jovan.core.domain.repository.IGithubRepository
import com.jovan.core.utils.DataMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val settingPreferences: SettingPreferences,
) : IGithubRepository {


    //    pref
    override fun getTheme(): Flow<Boolean> = settingPreferences.getThemeSetting()

    override suspend fun setThemeSetting(isDarkModeActive: Boolean) {
        settingPreferences.saveThemeSetting(isDarkModeActive)
    }

    //Local
    override fun getAllFavoriteUser(): Flow<List<User>> {
        return localDataSource.getAllFavoriteUser().map { userEntity ->
            DataMapper.mapUserListEntitiesToResponse(userEntity).map { userResponse ->
                DataMapper.mapUserResponseToDomain(userResponse)
            }
        }
    }

    override fun insertFavoriteUser(userEntity: User) {
        CoroutineScope(Dispatchers.IO).launch {
            val userList = DataMapper.mapUserDomainToEntity(userEntity)
            localDataSource.insert(userList)
        }
    }

    override fun deleteFavoriteUser(userEntity: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userEntity.let {
                val userList = DataMapper.mapUserDomainToEntity(it)

                localDataSource.delete(userList)}
        }
    }

    override fun getFavoriteUser(id: Int): Flow<User?> {

        val userEntity = localDataSource.getFavoriteUser(id).map { entity ->
            entity?.let {
                // If the entity is not null, map it to a response
                DataMapper.mapUserEntitiesToResponse(it)
            }
        }.map { response ->
                response?.let {
                    // If the response is not null, map it to the domain model
                    DataMapper.mapUserResponseToDomain(it)
                }
            }

        return userEntity
    }

    //Remote

    override suspend fun getSearchUser(query: String): Flow<ApiResponse<UserList>> {

        return remoteDataSource.getSearchUser(query).map {
            when (it) {
                is ApiResponse.Success -> ApiResponse.Success(
                    DataMapper.mapUserListResponsesToDomain(
                        it.data
                    )
                )

                is ApiResponse.Empty -> ApiResponse.Empty
                is ApiResponse.Error -> ApiResponse.Error(it.errorMessage)
            }
        }
    }

    override suspend fun getUserDetail(username: String): DetailUser {
        return DataMapper.mapDetailUserResponseToDomain(remoteDataSource.getUserDetail(username))
    }

    override suspend fun getFollowers(username: String): List<User> {
        return remoteDataSource.getFollowers(username).map {
            DataMapper.mapUserResponseToDomain(it)
        }
    }

    override suspend fun getFollowing(username: String): List<User> {

        return remoteDataSource.getFollowing(username).map {
            DataMapper.mapUserResponseToDomain(it)
        }
    }

}