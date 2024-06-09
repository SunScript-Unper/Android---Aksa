package com.example.aksa.database.hasilQuiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HasilQuizViewModel(private val hasilQuizRepository: HasilQuizRepository) : ViewModel() {

    private val _hasilQuiz = MutableLiveData<Boolean>()
    val hasilQuiz: LiveData<Boolean> get() = _hasilQuiz

    fun getAllHasilQuiz(): LiveData<List<HasilQuizEntity>> {
        return hasilQuizRepository.getAllHasilQuiz()
    }

    fun insertHasilQuiz(hasilQuiz: HasilQuizEntity) {
        viewModelScope.launch {
            try {
                hasilQuizRepository.insertHasilQuiz(hasilQuiz)
                _hasilQuiz.value = true
            } catch (e: Exception) {
                _hasilQuiz.value = false
            }
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            hasilQuizRepository.deleteAll()
        }
    }

}