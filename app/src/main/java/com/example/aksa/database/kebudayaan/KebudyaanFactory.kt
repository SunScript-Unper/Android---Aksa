package com.example.aksa.database.kebudayaan

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class KebudyaanFactory(private val kebudayaanRepository: KebudayaanRepository): ViewModelProvider.Factory {

    companion object {
        @Volatile
        private var INSTANCE : KebudyaanFactory? = null

        fun getInstance(context: Context): KebudyaanFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: KebudyaanFactory(
                    KebudayaanRepository.getInstance(context)
                )
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(KebudayaanViewModel::class.java) ->{
                KebudayaanViewModel(kebudayaanRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}