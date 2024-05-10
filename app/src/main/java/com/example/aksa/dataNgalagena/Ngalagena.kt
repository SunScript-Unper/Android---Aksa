package com.example.aksa.dataNgalagena

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ngalagena(
    val id: Int,
    val name: String,
    val image: Int
) : Parcelable
