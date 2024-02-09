package com.example.aksa.model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aksa.di.Injection
import com.example.aksa.di.MachineInjection
import com.example.aksa.repository.MachineRepository

class PredictionFactory(private val machineRepository: MachineRepository) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(PredictionViewModel::class.java) -> {
                PredictionViewModel(machineRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: PredictionFactory? = null

        @JvmStatic
        fun getInstance(context: Context): PredictionFactory {
            if (instance == null) {
                synchronized(PredictionFactory::class.java) {
                    instance = PredictionFactory(
                        MachineInjection.provideRepository(context)
                    )
                }
            }
            return instance as PredictionFactory
        }
    }
}