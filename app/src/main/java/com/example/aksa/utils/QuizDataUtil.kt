package com.example.aksa.utils

import com.example.aksa.R

object QuizDataUtil {

    val questions = listOf(
        Question(
            text = "Aksara Sunda diatas ini termasuk jenis aksara sunda?",
            options = arrayOf("Aksara Swara", "Aksara Ngalagena", "Aksara Sunda", "Aksara Kidul"),
            correctAnswerIndex = 1,
            imageId = R.drawable.aksara_ka
        ),
        Question(
            text = "Aksara sunda apa yang ada digambar ini?",
            options = arrayOf("da", "ba", "la", "ja"),
            correctAnswerIndex = 0,
            imageId = R.drawable.aksara_da
        ),
        Question(
            text = "Aksara sunda apa yang ada digambar ini?",
            options = arrayOf("a", "e", "o", "ea"),
            correctAnswerIndex = 2,
            imageId = R.drawable.aksara_o
        ),
        Question(
            text = "Aksara sunda apa yang ada digambar ini?",
            options = arrayOf("ka", "sa", "pa", "na"),
            correctAnswerIndex = 2,
            imageId = R.drawable.aksara_pa
        ),
        Question(
            text = "Aksara sunda apa yang ada digambar ini?",
            options = arrayOf("é", "u", "eu", "i"),
            correctAnswerIndex = 3,
            imageId = R.drawable.aksara_i
        )
    )

}