package com.example.aksa.utils

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class QuizManagerTest {

    private lateinit var quizManager: QuizManager

    @Before
    fun setUp() {
        quizManager = QuizManager(QuizDataUtil.questions)
    }

    @Test
    fun testFinalScoreIs100WhenAllAnswersAreCorrect() {
        QuizDataUtil.questions.forEach { question ->
            quizManager.submitAnswer(question.correctAnswerIndex)
            quizManager.moveToNextQuestion()
        }
        assertEquals(100, quizManager.score)
    }

    @Test
    fun testFinalScoreIsZeroWhenAllAnswersAreWrong() {
        QuizDataUtil.questions.forEach { question ->
            quizManager.submitAnswer((question.correctAnswerIndex + 1) % 4)
            quizManager.moveToNextQuestion()
        }
        assertEquals(0, quizManager.score)
    }

}