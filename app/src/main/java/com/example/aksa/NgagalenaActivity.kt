package com.example.aksa

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aksa.adapter.NgalagenaAdapter
import com.example.aksa.dataNgalagena.DataNgalagena
import com.example.aksa.databinding.ActivityNgagalenaBinding

class NgagalenaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNgagalenaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityNgagalenaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = NgalagenaAdapter()
        binding.rvNgalagena.adapter = adapter
        binding.rvNgalagena.setHasFixedSize(true)
        adapter.submitList(DataNgalagena.listNgalagena)
    }
}