package com.example.aksa.database.kebudayaan

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
@Entity(tableName = "culture")
data class CultureEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val image : String,
    val title : String,
    val desc : String
) : Parcelable
