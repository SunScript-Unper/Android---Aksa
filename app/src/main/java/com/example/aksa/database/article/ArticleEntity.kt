package com.example.aksa.database.article

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "article")
@Parcelize
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,

    val title : String,

    val description : String,

    val image : Int
) : Parcelable
