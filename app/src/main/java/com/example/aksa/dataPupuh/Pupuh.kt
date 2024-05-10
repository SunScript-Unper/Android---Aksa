package com.example.aksa.dataPupuh

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pupuh(
    val id: Int,
    val name: String,
    val desc: String,
    val pupuh: String
): Parcelable
