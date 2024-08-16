package com.jovan.github_user_app.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jovan.core.domain.usecase.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(private val githubUseCase: GithubUseCase) : ViewModel() {

    fun getThemeSettings(): LiveData<Boolean> = githubUseCase.getTheme().asLiveData()

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            githubUseCase.setThemeSetting(isDarkModeActive)
        }
    }
}