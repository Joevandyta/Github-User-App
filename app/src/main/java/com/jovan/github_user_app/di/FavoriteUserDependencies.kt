package com.jovan.github_user_app.di

import com.jovan.core.domain.usecase.GithubUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteUserDependencies {
    fun githubUseCase(): GithubUseCase
}