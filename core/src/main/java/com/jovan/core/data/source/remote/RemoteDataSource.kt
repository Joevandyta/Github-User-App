package com.jovan.core.data.source.remote

import android.util.Log
import com.jovan.core.data.source.remote.network.ApiResponse
import com.jovan.core.data.source.remote.network.ApiService
import com.jovan.core.data.source.remote.response.DetailUserResponse
import com.jovan.core.data.source.remote.response.UserListResponse
import com.jovan.core.data.source.remote.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getSearchUser(query: String): Flow<ApiResponse<UserListResponse>> {
        return flow {
            try {

                val response = apiService.getSearchUser(query)
                val users = response.items
                if (users.isNotEmpty()) {

                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUserDetail(username: String): DetailUserResponse {
        return apiService.getUserDetail(username)
    }

    suspend fun getFollowers(username: String): List<UserResponse> = apiService.getFollowers(username)

    suspend fun getFollowing(username: String): List<UserResponse> {
        return apiService.getFollowing(username)
    }


}