package com.example.aksa.dataSwara

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Swara(
    val id: Int,
    val name: String,
    val image: Int
) : Parcelable
