package com.jovan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailUserResponse(
    val login: String,
    val id : Int,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("followers_url")
    val followersUrl: String,

    @field:SerializedName("following_url")
    val followingUrl: String,
    val name: String?,
    val followers: Int,
    val following: Int
)
