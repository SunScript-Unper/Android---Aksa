package com.example.aksa.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aksa.repository.UserRepository
import com.example.aksa.response.LoginRequest
import com.example.aksa.response.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository): ViewModel() {

    private val _login = MutableLiveData<LoginResponse>()
    val login : LiveData<LoginResponse> = _login

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun login(loginRequest: LoginRequest) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.login(loginRequest)
                _login.value = response
                _loading.value = false
            } catch (e: Exception) {
                _loading.value = false
            }
        }
    }
}