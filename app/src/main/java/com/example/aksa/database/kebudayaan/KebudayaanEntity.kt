package com.example.aksa.database.kebudayaan

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "kebudayaan")
data class KebudayaanEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val image : String,
    val title : String,
    val desc : String
) : Parcelable
