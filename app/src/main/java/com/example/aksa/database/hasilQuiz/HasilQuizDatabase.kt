package com.example.aksa.database.hasilQuiz

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HasilQuizEntity::class], version = 1, exportSchema = false)
abstract class HasilQuizDatabase : RoomDatabase() {

    abstract fun hasilQuizDao(): HasilQuizDao


    companion object {
        private const val DATABASE_NAME = "hasilquiz_database"

        @Volatile
        private var INSTANCE: HasilQuizDatabase? = null

        fun getInstance(context: Context): HasilQuizDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HasilQuizDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}