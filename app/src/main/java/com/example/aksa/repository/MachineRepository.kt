package com.example.aksa.repository

import com.example.aksa.remote.ApiService
import com.example.aksa.remote.machine.ApiServiceMachine
import com.example.aksa.response.PredictionResponse
import okhttp3.MultipartBody

class MachineRepository(private val apiServiceMachine: ApiServiceMachine) {

    suspend fun predict(file: MultipartBody.Part): PredictionResponse {
        return apiServiceMachine.predict(file)
    }

    companion object {
        @Volatile
        private var instance: MachineRepository? = null

        fun getInstance(apiServiceMachine: ApiServiceMachine): MachineRepository =
            instance ?: synchronized(this) {
                instance ?: MachineRepository(apiServiceMachine)
            }.also { instance = it }
    }
}