package com.example.aksa.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aksa.repository.MachineRepository
import com.example.aksa.response.PredictionResponse
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class PredictionViewModel(private val machineRepository: MachineRepository) : ViewModel() {

    private val _prediction = MutableLiveData<PredictionResponse>()
    val prediction: LiveData<PredictionResponse> = _prediction

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun predict(file: MultipartBody.Part) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = machineRepository.predict(file)
                _prediction.value = response
                _isLoading.value = false
            } catch (e: Exception) {
                _isLoading.value = false
            }
        }
    }
}