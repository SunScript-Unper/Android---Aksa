package com.example.aksa.di

import android.content.Context
import com.example.aksa.remote.ApiConfig
import com.example.aksa.repository.UserRepository

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService)
    }
}