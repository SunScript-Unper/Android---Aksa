package com.example.aksa.database.kebudayaan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [CultureEntity::class], version = 1, exportSchema = false)
abstract class CultureDatabase : RoomDatabase() {

    abstract fun cultureDao(): KebudayaanDao

    companion object {
        const val DATABASE_NAME = "culture_database"

        @Volatile
        private var INSTANCE : CultureDatabase? = null

        fun getInstance(context: Context) : CultureDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CultureDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }

        }
    }
}