package com.example.aksa.dataKebudayaan

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Kebudayaan(
    val id : Int,
    val img : Int,
    val title : String,
    val desc1 : String,
    val desc2 : String,
    val desc3 : String,
    val desc4 : String,
    val desc5 : String
) : Parcelable
