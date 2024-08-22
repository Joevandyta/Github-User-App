package com.jovan.core.domain.model

data class DetailUser(
    val login: String,
    val id : Int,
    val avatarUrl: String,
    val followersUrl: String,
    val followingUrl: String,
    val name: String?,
    val followers: Int,
    val following: Int
)