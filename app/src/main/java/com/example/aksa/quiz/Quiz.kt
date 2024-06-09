package com.example.aksa.quiz

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quiz(
    val id: Int,
    val name: String,
) : Parcelable
