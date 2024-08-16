package com.jovan.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserEntity (
    @PrimaryKey(autoGenerate = false)

    @ColumnInfo(name = "id")
    var id : Int,

    @ColumnInfo(name = "login")
    var login: String,

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String,
)