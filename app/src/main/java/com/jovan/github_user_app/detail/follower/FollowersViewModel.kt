package com.jovan.github_user_app.detail.follower

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
class FollowersViewModel @Inject constructor(private val githubUseCase: GithubUseCase) : ViewModel() {
    private val listFollowers = MutableLiveData<List<User>>()

    fun setListFollowers(username: String) {
        viewModelScope.launch {
            githubUseCase.getFollowers(username).let {
                listFollowers.postValue(it)
            }
        }
    }

    fun getListFollowers(): LiveData<List<User>> {
        return listFollowers
    }
}