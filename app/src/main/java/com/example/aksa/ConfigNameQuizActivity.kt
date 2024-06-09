package com.example.aksa

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aksa.databinding.ActivityConfigNameQuizBinding
import com.example.aksa.pref.user.User
import com.example.aksa.pref.user.UserPreference
import com.example.aksa.pref.user.dataStore
import com.example.aksa.quiz.QuizAksaraSundaActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConfigNameQuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigNameQuizBinding
    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityConfigNameQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userPreference = UserPreference.getInstance(this.dataStore)

        binding.btnLanjutQuiz.setOnClickListener {
            val name = binding.editTextName.text.toString()
            CoroutineScope(Dispatchers.Main).launch {
                userPreference.saveName(User(name))
            }
            if (name.isEmpty()) {
                Toast.makeText(this, "Masukkan Nama Kamu", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}