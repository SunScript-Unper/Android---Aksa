package com.example.aksa.quiz

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aksa.R
import com.example.aksa.databinding.ActivityQuizPupuhBinding
import com.example.aksa.pref.dataStore
import com.example.aksa.pref.user.NamaKuis
import com.example.aksa.pref.user.UserPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuizPupuhActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizPupuhBinding
    private lateinit var textPupuh : TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var radioGroupOptions: RadioGroup
    private lateinit var radioButtonOption1: RadioButton
    private lateinit var radioButtonOption2: RadioButton
    private lateinit var radioButtonOption3: RadioButton
    private lateinit var radioButtonOption4: RadioButton
    private lateinit var buttonSubmit: Button
    private lateinit var userPreference: UserPreference

    private val questions = arrayOf(
        "Ini termasuk jenis pupuh?",
        "Lanjutkan pupuh maskumambang ini ",
        "Ini termasuk jenis Pupuh?",
        "Mana yang termasuk pupuh Durma?",
        "Ini termasuk jenis Pupuh?"
    )
    private val options = arrayOf(
        arrayOf("Pupuh Balakbak", "Pupuh Pucung", "Pupuh Durma", "Pupuh Asmarandana"),
        arrayOf("Rahayu jeung loba harta", "Néangan nu amis-amis", "Teu aya pisan ras-rasan", "Panon poé arek bijil"),
        arrayOf("Pupuh Jurudemung", "Pupuh Balakbak", "Pupuh Mijil", "Pupuh Kinanti"),
        arrayOf("Ti isuk kuring ngagidig leumpang\n" +
                "kahayang geura nepi\n" +
                "ka pangumbaraan\n" +
                "nu geus diimpikeun lila\n" +
                "sumanget gede dina hate\n" +
                "nyiar pakaya\n" +
                "supaya tengtrem hate",
            "Nawu kubang sisi tegal\n" +
                    "Nyiar bogo meunang kadal\n" +
                    "Atuh teu payu dijual\n" +
                    "Rek didahar da teu halal\n",
            "Lutung buntung luncat kana tunggul gintung\n" +
                    "Monyet lerang leupas\n" +
                    "Luncat kana pager déngdék\n" +
                    "Bajing kuning jaralang belang buntutna\n",
            "Hei manusa mana kaniyaya teuing\n" +
                    "Teu aya rasrasan\n" +
                    "Kawula maké disumpit\n" +
                    "Naha naon dosa kula\n"),
        arrayOf("Pupuh Wirangrong", "Pupuh Pangkur", "Pupuh Magatru", "Pupuh Lambang")
    )
    private val correctAnswers = intArrayOf(0, 2, 3, 0, 0) // Index of correct answers for each question
    private val pupuh = arrayOf(
        "Aya warung sisi jalan ramé pisan, citaméng\n" +
                "Awéwéna luas luis geulis pisan, ngagoréng\n" +
                "Lalakina-lalakina los ka pipir nyo monyet, nyangéréng\n",
        "Itu kusir bangun ambek-ambek teuing (12-i)\n" +
                "Turun tina delman (6-a)\n" +
                "Kuda dipecutan tarik (8-i)\n" +
                "lanjutkan..............(8-a)\n",
        "Budak leutik bisa ngapung\n" +
                "Babaku ngapungna peuting\n" +
                "Nguriling kakalayangan\n" +
                "Néangan nu amis-amis\n" +
                "Sarupaning bungbuahan\n" +
                "Naon baé nu kapanggih\n",
        "Guru wilangan dan guru lagu pupuh durma: 12a, 7i, 6a, 7a, 8i, 5a, 7i.\n" +
                "Padalisan ke-1: 12 suku kata, suara vokal ujung baris (a)\n" +
                "Padalisan ke-1: 7 suku kata, suara vokal ujung baris (i)\n" +
                "Padalisan ke-1: 6 suku kata, suara vokal ujung baris (a)\n" +
                "Padalisan ke-1: 7 suku kata, suara vokal ujung baris (a)\n" +
                "Padalisan ke-1: 8 suku kata, suara vokal ujung baris (i)\n" +
                "Padalisan ke-1: 5 suku kata, suara vokal ujung baris (a)\n" +
                "Padalisan ke-1: 7 suku kata, suara vokal ujung baris (i)",
        "Barudak mangka ngalarti\n" +
                "Ulah rék kadalon-dalon\n" +
                "Enggon-enggon nuntut élmu\n" +
                "Mangka getol mangka tigin\n" +
                "Pibekeleun sararéa\n" +
                "Modal bakti ka nagara\n"

    )

    private var currentQuestionIndex = 0
    private var score = 0 // Variable untuk menyimpan nilai

    var quizName : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuizPupuhBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userPreference = UserPreference.getInstance(this.dataStore)

        textPupuh = findViewById(R.id.tv_pupuh_quiz)
        textViewQuestion = findViewById(R.id.textViewQuestion)
        radioGroupOptions = findViewById(R.id.radioGroupOptions)
        radioButtonOption1 = findViewById(R.id.radioButtonOption1)
        radioButtonOption2 = findViewById(R.id.radioButtonOption2)
        radioButtonOption3 = findViewById(R.id.radioButtonOption3)
        radioButtonOption4 = findViewById(R.id.radioButtonOption4)
        buttonSubmit = findViewById(R.id.buttonSubmit)

        displayQuestion()

        val quiz = intent.getStringExtra("KEY_PUPUH")

        if (quiz != null) {
            quizName = quiz

            CoroutineScope(Dispatchers.Main).launch {
                userPreference.saveNamaKuis(
                    NamaKuis(
                        quizName
                    )
                )
            }
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
                    intent.putExtra("hasil", score)
                    intent.putExtra("pupuh", quiz)
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
        textPupuh.text = pupuh[currentQuestionIndex]

        // Clear selection
        radioGroupOptions.clearCheck()
    }

    private fun finishQuiz() {
        val quiz = intent.getParcelableExtra<Quiz>("pupuh")
        val intent = Intent(this, HasilQuizActivity::class.java)
        intent.putExtra("hasil", score)
        intent.putExtra("pupuh", quiz)
        startActivity(intent)
        finish()
    }
}