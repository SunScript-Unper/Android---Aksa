package com.example.aksa.utils

data class Question(
    val text: String,
    val options: Array<String>,
    val correctAnswerIndex: Int,
    val imageId: Int
)

class QuizManager(private val questions: List<Question>) {
    private var currentQuestionIndex = 0
    var score = 0
        private set

    fun getCurrentQuestion(): Question {
        return questions[currentQuestionIndex]
    }

    fun submitAnswer(answerIndex: Int): Boolean {
        val correct = answerIndex == getCurrentQuestion().correctAnswerIndex
        if (correct) {
            score += 20
        }
        return correct
    }

    fun moveToNextQuestion(): Boolean {
        return if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            true
        } else {
            false
        }
    }
}