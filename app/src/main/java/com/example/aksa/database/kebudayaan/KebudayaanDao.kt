package com.example.aksa.database.kebudayaan

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface KebudayaanDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKebudayaan(kebudayaanEntity: CultureEntity)

    @Query("SELECT * FROM culture ORDER BY id DESC")
    fun getAllKebudayaan(): PagingSource<Int, CultureEntity>

    @Query("DELETE FROM culture WHERE id = :id")
    suspend fun deleteKebudayaan(id: Long)
}