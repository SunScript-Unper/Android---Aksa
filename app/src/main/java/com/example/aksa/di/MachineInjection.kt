package com.example.aksa.di

import android.content.Context
import com.example.aksa.remote.machine.ApiConfigMachine
import com.example.aksa.repository.MachineRepository

object MachineInjection {
    fun provideRepository(context: Context): MachineRepository {
        val apiServiceMachine = ApiConfigMachine.getApiServiceMachine()
        return MachineRepository.getInstance(apiServiceMachine)
    }
}