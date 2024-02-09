package com.example.aksa.database.article

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Article(
    val id : Int,
    val title : String,
    val description : String,
    val image : Int
) : Parcelable
