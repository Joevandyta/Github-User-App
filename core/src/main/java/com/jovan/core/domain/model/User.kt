package com.jovan.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val login: String,
    val avatarUrl: String,
):Parcelable