package com.example.aksa.database.hasilQuiz

import android.content.Context
import androidx.lifecycle.LiveData

class HasilQuizRepository(private val hasilquizDao: HasilQuizDao) {


    fun getAllHasilQuiz(): LiveData<List<HasilQuizEntity>> {
        return hasilquizDao.getAllHasilQuiz()
    }

    suspend fun insertHasilQuiz(hasilQuiz: HasilQuizEntity) {
        hasilquizDao.insertHasilQuiz(hasilQuiz)
    }

    suspend fun deleteAll() {
        hasilquizDao.deleteAllHasilQuiz()
    }


    companion object {

        @Volatile
        private var instance: HasilQuizRepository? = null

        fun getInstance(context: Context) : HasilQuizRepository {
            return instance ?: synchronized(this){
                if (instance == null) {
                    val database = HasilQuizDatabase.getInstance(context)
                    instance = HasilQuizRepository(database.hasilQuizDao())
                }
                return instance as HasilQuizRepository
            }
        }
    }

}