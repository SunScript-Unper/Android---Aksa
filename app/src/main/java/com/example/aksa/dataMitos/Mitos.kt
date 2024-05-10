package com.example.aksa.dataMitos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mitos(
    val id: Int,
    val name: String,
    val img: Int,
    val desc: String
): Parcelable
