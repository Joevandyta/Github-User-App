package com.jovan.github_user_app.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jovan.core.data.source.remote.network.ApiResponse
import com.jovan.core.domain.model.UserList
import com.jovan.core.domain.usecase.GithubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@FlowPreview
@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(private val githubUseCase: GithubUseCase) : ViewModel() {

    val setSearchUser = MutableStateFlow("")

    val searchQuery = setSearchUser.debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .flatMapLatest {
            githubUseCase.getSearchUser(it)
        }.asLiveData()
}
