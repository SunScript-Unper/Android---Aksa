package com.example.aksa.database.hasilQuiz

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "hasilquiz")
data class HasilQuizEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val namaQuiz: String,
    val nilaiQuiz : Int,
) : Parcelable
