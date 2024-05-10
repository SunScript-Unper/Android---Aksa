package com.example.aksa

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aksa.adapter.SwaraAdapter
import com.example.aksa.dataSwara.DataSwara
import com.example.aksa.databinding.ActivitySwaraBinding

class SwaraActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySwaraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySwaraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = SwaraAdapter()
        binding.rvSwara.adapter = adapter
        binding.rvSwara.setHasFixedSize(true)
        adapter.submitList(DataSwara.listSwara)
    }
}