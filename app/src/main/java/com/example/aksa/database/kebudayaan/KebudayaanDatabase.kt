package com.example.aksa.database.kebudayaan

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [CultureEntity::class], version = 1, exportSchema = false)
abstract class KebudayaanDatabase : RoomDatabase() {


    abstract fun kebudayaanDao(): KebudayaanDao

    companion object {
        const val DATABASE_NAME = "kebudayaan_database"

        @Volatile
        private var INSTANCE : KebudayaanDatabase? = null

        fun getInstance(context: Context) : KebudayaanDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KebudayaanDatabase::class.java,
                    DATABASE_NAME
                )
                    .build()
                INSTANCE = instance
                instance
            }

        }


    }



//    abstract fun kebudayaanDao(): KebudayaanDao
//
//    companion object {
//        const val DATABASE_NAME = "kebudayaan_database"
//
//        @Volatile
//        private var INSTANCE : KebudayaanDatabase? = null
//
//        fun getInstance(context: Context) : KebudayaanDatabase {
//            return INSTANCE ?: synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    KebudayaanDatabase::class.java,
//                    DATABASE_NAME
//                )
//                    .addMigrations(MIGRATION_1_2)
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//
//        }
//
//        private val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                // Menjalankan perintah SQL untuk mengubah tipe data kolom image dari INTEGER menjadi TEXT
//                database.execSQL("ALTER TABLE kebudayaan ADD COLUMN image TEXT DEFAULT ''")
//            }
//        }
//    }
}