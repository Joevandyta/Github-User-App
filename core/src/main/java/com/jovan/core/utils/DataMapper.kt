package com.jovan.core.utils

import com.jovan.core.data.source.local.entity.UserEntity
import com.jovan.core.data.source.remote.response.DetailUserResponse
import com.jovan.core.data.source.remote.response.UserListResponse
import com.jovan.core.data.source.remote.response.UserResponse
import com.jovan.core.domain.model.DetailUser
import com.jovan.core.domain.model.User
import com.jovan.core.domain.model.UserList

object DataMapper {


    //Details User
    fun mapDetailUserResponseToDomain(input: DetailUserResponse) = DetailUser(
        login = input.login,
        id = input.id,
        avatarUrl = input.avatarUrl,
        followersUrl = input.followersUrl,
        followingUrl = input.followingUrl,
        name = input.name,
        followers = input.followers,
        following = input.following
    )

    //UserList
    fun mapUserListResponsesToDomain(input: UserListResponse): UserList {
        val list = UserList(ArrayList())
        input.items.map {
            val user = User(
                id = it.id,
                login = it.login,
                avatarUrl = it.avatarUrl
            )

            list.items.add(user)
        }
        return list
    }

    fun mapUserListEntitiesToResponse(input: List<UserEntity>): List<UserResponse> =
        input.map {
            UserResponse(
                id = it.id,
                login = it.login,
                avatarUrl = it.avatarUrl
            )
        }

    //User
    fun mapUserDomainToEntity(input: User) = UserEntity(
        id = input.id,
        login = input.login,
        avatarUrl = input.avatarUrl
    )


    fun mapUserEntitiesToResponse(input: UserEntity): UserResponse {
        return UserResponse(
            id = input.id,
            login = input.login,
            avatarUrl = input.avatarUrl
        )
    }

    fun mapUserResponseToDomain(input: UserResponse) = User(
        id = input.id,
        login = input.login,
        avatarUrl = input.avatarUrl
    )
}

