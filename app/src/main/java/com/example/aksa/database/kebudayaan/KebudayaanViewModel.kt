package com.example.aksa.database.kebudayaan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import kotlinx.coroutines.launch

class KebudayaanViewModel(private val kebudayaanRepository: KebudayaanRepository): ViewModel() {

    private val _insert = MutableLiveData<Boolean>()
    val insert: LiveData<Boolean> = _insert

    fun getAllKebudayaan(): LiveData<PagingData<CultureEntity>> {
        return kebudayaanRepository.getAllKebudayaan()
    }

    fun insertKebudayaan(kebudayaanEntity: CultureEntity){
        viewModelScope.launch {
            try {
                kebudayaanRepository.insertKebudayaan(kebudayaanEntity)
                _insert.value = true
            } catch (e: Exception){
                _insert.value = false
            }
        }
    }

    fun deleteKebudayaan(id: Long){
        viewModelScope.launch {
            kebudayaanRepository.deleteKebudayaan(id)
        }
    }
}