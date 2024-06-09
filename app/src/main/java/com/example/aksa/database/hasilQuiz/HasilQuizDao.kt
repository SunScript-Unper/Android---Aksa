package com.example.aksa.database.hasilQuiz

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HasilQuizDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHasilQuiz(hasilQuiz: HasilQuizEntity)

    @Query("SELECT * FROM hasilquiz ORDER BY id DESC")
    fun getAllHasilQuiz(): LiveData<List<HasilQuizEntity>>

    @Query("DELETE FROM hasilquiz")
    suspend fun deleteAllHasilQuiz()

}