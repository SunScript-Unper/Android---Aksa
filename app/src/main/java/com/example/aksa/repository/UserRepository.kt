package com.example.aksa.repository

import com.example.aksa.remote.ApiService
import com.example.aksa.response.LoginRequest
import com.example.aksa.response.LoginResponse
import com.example.aksa.response.RegisterRequest
import com.example.aksa.response.RegisterResponse

class UserRepository(private val apiService: ApiService) {

    suspend fun register(registerRequest: RegisterRequest): RegisterResponse {
        return apiService.register(registerRequest)
    }

    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return apiService.login(loginRequest)
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(apiService: ApiService): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService)
            }.also { instance = it }
    }
}