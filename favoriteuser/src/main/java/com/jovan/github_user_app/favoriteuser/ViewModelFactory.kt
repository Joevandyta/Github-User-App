package com.jovan.github_user_app.favoriteuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jovan.core.domain.usecase.GithubUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val githubUseCase: GithubUseCase):
ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteUserViewModel::class.java) -> {
                FavoriteUserViewModel(githubUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}