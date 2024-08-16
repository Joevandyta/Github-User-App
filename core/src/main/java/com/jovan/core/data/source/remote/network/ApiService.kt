package com.jovan.core.data.source.remote.network

import com.jovan.core.BuildConfig
import com.jovan.core.data.source.remote.response.UserListResponse
import com.jovan.core.data.source.remote.response.DetailUserResponse
import com.jovan.core.data.source.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    suspend fun getSearchUser(
        @Query("q") query: String
    ): UserListResponse

    @GET("users/{username}")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    suspend fun getUserDetail(
        @Path("username") username:String
    ): DetailUserResponse

    @GET("users/{username}/followers")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    suspend fun getFollowers(
        @Path("username") username:String
    ): ArrayList<UserResponse>

    @GET("users/{username}/following")
    @Headers("Authorization: token ${BuildConfig.API_KEY}")
    suspend fun getFollowing(
        @Path("username") username:String
    ): ArrayList<UserResponse>
}