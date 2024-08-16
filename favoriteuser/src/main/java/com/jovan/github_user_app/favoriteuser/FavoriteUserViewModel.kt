package com.jovan.github_user_app.favoriteuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jovan.core.domain.model.User
import com.jovan.core.domain.usecase.GithubUseCase

class FavoriteUserViewModel(private val githubUseCase: GithubUseCase): ViewModel(){
    fun getFavoriteUser(): LiveData<List<User>> = githubUseCase.getAllFavoriteUser().asLiveData()
}