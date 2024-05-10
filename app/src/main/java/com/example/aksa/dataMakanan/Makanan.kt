package com.example.aksa.dataMakanan

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Makanan(
    val id: Int,
    val name: String,
    val img: Int,
) : Parcelable
