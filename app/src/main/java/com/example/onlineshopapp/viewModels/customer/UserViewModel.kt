package com.example.onlineshopapp.viewModels.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.cusomer.UserVm
import com.example.onlineshopapp.models.cusomer.Users
import com.example.onlineshopapp.repositories.customer.UserRepository
import com.example.onlineshopapp.utils.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    var token: String = ThisApp.token
    fun getUsersInfo(
        onResponse: (response: ServiceResponse<Users>)
        -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.getUserInfo(token)
            onResponse(response)
        }
    }

    fun changePassword(
        users: UserVm, onResponse: (response: ServiceResponse<Users>)
        -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.changePassword(users, token)
            onResponse(response)
        }
    }

    fun login(
        users: UserVm, onResponse: (response: ServiceResponse<UserVm>)
        -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.login(users)
            onResponse(response)
        }
    }

    fun register(
        users: UserVm, onResponse: (response: ServiceResponse<Users>)
        -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.register(users)
            onResponse(response)
        }
    }

    fun update(
        users: UserVm, onResponse: (response: ServiceResponse<Users>)
        -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.update(users, token)
            onResponse(response)
        }
    }
}