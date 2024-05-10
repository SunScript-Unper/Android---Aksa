package com.example.aksa

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aksa.dataPupuh.Pupuh
import com.example.aksa.databinding.ActivityDetailPupuhBinding
import com.example.aksa.databinding.ActivityListPupuhBinding

class DetailPupuhActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailPupuhBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailPupuhBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pupuh = intent.getParcelableExtra<Pupuh>("pupuh")
        binding.apply {
            if (pupuh != null) {
                tvNamaPupuh.text = pupuh.name
                tvDescPupuh.text = pupuh.desc
                tvLaguPupuh.text = pupuh.pupuh
            }
        }
    }
}