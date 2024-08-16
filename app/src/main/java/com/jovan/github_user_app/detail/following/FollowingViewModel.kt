package com.jovan.github_user_app.detail.following

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jovan.core.domain.model.User
import com.jovan.core.domain.usecase.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FollowingViewModel @Inject constructor(private val githubUseCase: GithubUseCase): ViewModel() {

    private val listFollowing = MutableLiveData<List<User>>()

    fun setListFollowing(username: String) {
        viewModelScope.launch {
            listFollowing.postValue(githubUseCase.getFollowing(username))
        }
    }

    fun getListFollowing(): LiveData<List<User>> {
        return listFollowing
    }
}