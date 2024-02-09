package com.example.aksa.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aksa.repository.UserRepository
import com.example.aksa.response.RegisterRequest
import com.example.aksa.response.RegisterResponse
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository): ViewModel() {

    private val _register = MutableLiveData<RegisterResponse>()
    val register: LiveData<RegisterResponse> = _register

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading


    fun register(registerRequest: RegisterRequest) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.register(registerRequest)
                _register.value = response
                _loading.value = false
            } catch (e: Exception) {
                _loading.value = false
            }
        }

    }
}