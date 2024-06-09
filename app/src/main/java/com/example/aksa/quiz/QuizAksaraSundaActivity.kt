package com.example.aksa.quiz
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aksa.R
import com.example.aksa.databinding.ActivityQuizAksaraSundaBinding
import com.example.aksa.pref.dataStore
import com.example.aksa.pref.user.NamaKuis
import com.example.aksa.pref.user.User
import com.example.aksa.pref.user.UserPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizAksaraSundaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizAksaraSundaBinding

    private lateinit var imageViewQuestion: ImageView
    private lateinit var textViewQuestion: TextView
    private lateinit var radioGroupOptions: RadioGroup
    private lateinit var radioButtonOption1: RadioButton
    private lateinit var radioButtonOption2: RadioButton
    private lateinit var radioButtonOption3: RadioButton
    private lateinit var radioButtonOption4: RadioButton
    private lateinit var buttonSubmit: Button
    private lateinit var userPreference: UserPreference

    // Array to hold questions, options, correct answers, and image IDs
    private val questions = arrayOf(
        "Aksara Sunda diatas ini termasuk jenis aksara sunda?",
        "Aksara sunda apa yang ada digambar ini?",
        "Aksara sunda apa yang ada digambar ini?",
        "Aksara sunda apa yang ada digambar ini?",
        "Aksara sunda apa yang ada digambar ini??"
    )
    private val options = arrayOf(
        arrayOf("Aksara Swara", "Aksara Ngalagena", "Aksara Sunda", "Aksara Kidul"),
        arrayOf("da", "ba", "la", "ja"),
        arrayOf("a", "e", "o", "ea"),
        arrayOf("ka", "sa", "pa", "na"),
        arrayOf("é", "u", "eu", "i")
    )
    private val correctAnswers = intArrayOf(1, 0, 2, 2, 3) // Index of correct answers for each question
    private val imageIds = intArrayOf(
        R.drawable.aksara_ka,
        R.drawable.aksara_da,
        R.drawable.aksara_o,
        R.drawable.aksara_pa,
        R.drawable.aksara_i
    )

    private var currentQuestionIndex = 0
    private var score = 0 // Variable untuk menyimpan nilai

    var quizName : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuizAksaraSundaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userPreference = UserPreference.getInstance(this.dataStore)



        imageViewQuestion = findViewById(R.id.imageViewQuestion)
        textViewQuestion = findViewById(R.id.textViewQuestion)
        radioGroupOptions = findViewById(R.id.radioGroupOptions)
        radioButtonOption1 = findViewById(R.id.radioButtonOption1)
        radioButtonOption2 = findViewById(R.id.radioButtonOption2)
        radioButtonOption3 = findViewById(R.id.radioButtonOption3)
        radioButtonOption4 = findViewById(R.id.radioButtonOption4)
        buttonSubmit = findViewById(R.id.buttonSubmit)

        displayQuestion()

        val quiz = intent.getStringExtra("KEY_AKSARA")

        if (quiz != null) {
            quizName = quiz

            CoroutineScope(Dispatchers.Main).launch {
                userPreference.saveNamaKuis(
                    NamaKuis(quizName)
                )
                Log.d("NAMA KUIS", quizName)
            }
        } else {
            Log.d("NAMA KUIS", "Tidak ada data")
        }


        buttonSubmit.setOnClickListener {
            val selectedOption = radioGroupOptions.checkedRadioButtonId
            var answerIndex = -1

            when (selectedOption) {
                R.id.radioButtonOption1 -> answerIndex = 0
                R.id.radioButtonOption2 -> answerIndex = 1
                R.id.radioButtonOption3 -> answerIndex = 2
                R.id.radioButtonOption4 -> answerIndex = 3
            }

            if (answerIndex == -1) {
                AlertDialog.Builder(this)
                    .setTitle("Jawaban belum dipilih")
                    .setMessage("Pilih jawaban terlebih dahulu!")
                    .setPositiveButton("OK") { _, _ ->

                    }
                    .show()
            } else {
                if (answerIndex == correctAnswers[currentQuestionIndex]) {
                    score += 20 // Menambahkan skor jika jawaban benar
                }

                if (currentQuestionIndex < questions.size - 1) {
                    currentQuestionIndex++
                    displayQuestion()
                } else {
                    // Mengirim nilai ke ResultActivity saat kuis selesai
                    val intent = Intent(this, HasilQuizActivity::class.java)
                    intent.putExtra("SCORE", score)
                    startActivity(intent)
                    finish() // Mengakhiri MainActivity setelah pindah ke ResultActivity
                }
            }


        }



    }

    private fun displayQuestion() {
        // Set question, options, and image
        textViewQuestion.text = questions[currentQuestionIndex]
        radioButtonOption1.text = options[currentQuestionIndex][0]
        radioButtonOption2.text = options[currentQuestionIndex][1]
        radioButtonOption3.text = options[currentQuestionIndex][2]
        radioButtonOption4.text = options[currentQuestionIndex][3]
        imageViewQuestion.setImageResource(imageIds[currentQuestionIndex])

        // Clear selection
        radioGroupOptions.clearCheck()
    }

    private fun finishQuiz() {

        val intent = Intent(this, HasilQuizActivity::class.java)
        intent.putExtra("SCORE", score)
        startActivity(intent)
        finish()
    }

}