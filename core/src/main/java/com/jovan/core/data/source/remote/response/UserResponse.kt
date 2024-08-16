package com.jovan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    val id : Int,
    val login: String,
    @field:SerializedName("avatar_url")
    val avatarUrl: String,
)
