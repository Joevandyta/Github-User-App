package com.jovan.core.di

import com.jovan.core.data.GithubRepository
import com.jovan.core.domain.repository.IGithubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class, PreferenceModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(githubRepository: GithubRepository): IGithubRepository
}