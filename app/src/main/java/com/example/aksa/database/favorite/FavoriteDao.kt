package com.example.aksa.database.favorite

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHuruf(favoriteEntity: FavoriteEntity)

    @Query("SELECT * FROM favorite ORDER BY id DESC")
    fun getAllFavorite(): PagingSource<Int, FavoriteEntity>

    @Query("DELETE FROM favorite")
    suspend fun deleteAll()
}