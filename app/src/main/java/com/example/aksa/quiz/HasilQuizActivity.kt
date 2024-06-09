package com.example.aksa.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.aksa.MainActivity
import com.example.aksa.database.hasilQuiz.HasilQuizEntity
import com.example.aksa.database.hasilQuiz.HasilQuizFactory
import com.example.aksa.database.hasilQuiz.HasilQuizViewModel
import com.example.aksa.databinding.ActivityHasilQuizBinding
import com.example.aksa.pref.user.UserPreference
import com.example.aksa.pref.user.dataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HasilQuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHasilQuizBinding
    private lateinit var tvNilai :TextView
    private lateinit var tvMessage : TextView
    private lateinit var tvHasil : TextView
    private lateinit var tvNama : TextView
    private lateinit var userPreference: UserPreference
    private lateinit var hasilQuizViewModel: HasilQuizViewModel

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hasilQuizViewModel = ViewModelProvider(this, HasilQuizFactory.getInstance(this))[HasilQuizViewModel::class.java]

        tvNilai = binding.tvNilai
        tvHasil = binding.tvhasil


        userPreference = UserPreference.getInstance(this.dataStore)

        val nilai = intent.getIntExtra("SCORE", 0)
        val value = intent.getIntExtra("nilai", 0)
        val hasil = intent.getIntExtra("hasil", 0)

        val nilaiQuiz = if (nilai != 0) {
            nilai
        } else if (value != 0) {
            value
        } else {
            hasil
        }

//        val benar = intent.getIntExtra("benar", 0)
//        val salah = intent.getIntExtra("salah", 0)
        tvNilai.text = "Nilai : $nilaiQuiz"



        if (nilaiQuiz == 100) {
            tvHasil.text = "Selamat Kamu mendapat nilai A"
        }else if (nilaiQuiz >= 80) {
            tvHasil.text = "Selamat Kamu mendapat nilai B"
        } else if (nilaiQuiz >= 60) {
            tvHasil.text = "Kamu mendapat nilai C"
        } else if (nilaiQuiz >= 40) {
            tvHasil.text = "Kamu mendapat nilai D"
        } else if (nilaiQuiz >= 20) {
            tvHasil.text = "Kamu mendapat nilai E"
        } else if (nilaiQuiz >= 0) {
            tvHasil.text = "Kamu mendapat nilai E"
        }

        CoroutineScope(Dispatchers.Main).launch {
            userPreference.getNamaKuis()?.let { nama ->
                hasilQuizViewModel.insertHasilQuiz(
                    HasilQuizEntity(
                        namaQuiz = nama,
                        nilaiQuiz = nilaiQuiz
                    )
                )
            }
        }

        binding.btnBack.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                userPreference.deleteNamaKuis()
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}