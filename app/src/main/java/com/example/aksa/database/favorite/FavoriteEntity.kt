package com.example.aksa.database.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val huruf: String,
    val image : String
)