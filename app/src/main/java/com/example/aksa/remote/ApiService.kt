package com.example.aksa.remote

import com.example.aksa.response.LoginRequest
import com.example.aksa.response.LoginResponse
import com.example.aksa.response.PredictionResponse
import com.example.aksa.response.RegisterRequest
import com.example.aksa.response.RegisterResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @POST("api/auth/signup")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ) : RegisterResponse

    @POST("api/auth/signin")
    suspend fun login(
        @Body loginRequest : LoginRequest
    ) : LoginResponse


}