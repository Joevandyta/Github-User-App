package com.jovan.github_user_app.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jovan.core.domain.model.DetailUser
import com.jovan.core.domain.model.User
import com.jovan.core.domain.usecase.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailUserViewModel @Inject constructor(private val githubUseCase: GithubUseCase): ViewModel() {

    private val user = MutableLiveData<DetailUser>()


    fun setDetailUser(username: String) {
        viewModelScope.launch {
            githubUseCase.getUserDetail(username).let {
                user.postValue(it)
            }
        }
    }

    fun getDetailUser(): LiveData<DetailUser> {
        return user
    }

    fun addFavoriteUser(user: User) {
        githubUseCase.insertFavoriteUser(user)
    }

    fun deleteFavoriteUser(user: User) {
        githubUseCase.deleteFavoriteUser(user)
    }

    fun isFavoriteUser(id: Int): LiveData<User?>  = githubUseCase.getFavoriteUser(id).asLiveData()
}