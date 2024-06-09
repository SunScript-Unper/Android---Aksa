package com.example.aksa.database.hasilQuiz

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HasilQuizFactory(private val hasilQuizRepository: HasilQuizRepository) : ViewModelProvider.Factory {

    companion object {
        @Volatile
        private var instance: HasilQuizFactory? = null

        fun getInstance(context: Context): HasilQuizFactory =
            instance ?: synchronized(this){
                instance ?: HasilQuizFactory(
                    HasilQuizRepository.getInstance(context)
                )
            }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HasilQuizViewModel::class.java) -> {
                HasilQuizViewModel(hasilQuizRepository) as T
            }
            else -> throw IllegalArgumentException("Model Tidak Dikenal: " + modelClass.name)
        }
    }
}