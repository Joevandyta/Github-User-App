package com.jovan.github_user_app.di

import com.jovan.core.domain.usecase.GithubInteractor
import com.jovan.core.domain.usecase.GithubUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideGithubUseCase(githubInteractor: GithubInteractor): GithubUseCase
}