package com.jovan.github_user_app.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jovan.core.domain.usecase.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val githubUseCase: GithubUseCase) : ViewModel() {

    fun getThemeSettings(): LiveData<Boolean> = githubUseCase.getTheme().asLiveData()
}