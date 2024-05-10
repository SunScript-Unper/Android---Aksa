package com.example.aksa.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Knowledges(
    val id : Int,
    val img : Int,
    val title : String
) : Parcelable
