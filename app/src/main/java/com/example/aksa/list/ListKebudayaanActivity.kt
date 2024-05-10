package com.example.aksa.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.aksa.R
import com.example.aksa.adapter.KebudayaanAdapter
import com.example.aksa.admin.ui.AddKebudayaanActivity
import com.example.aksa.dataKebudayaan.DataKebudayaan
import com.example.aksa.database.kebudayaan.KebudayaanViewModel
import com.example.aksa.database.kebudayaan.KebudyaanFactory
import com.example.aksa.databinding.ActivityListKebudayaanBinding

class ListKebudayaanActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListKebudayaanBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListKebudayaanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = KebudayaanAdapter()
        binding.rvKebudayaan.adapter = adapter
        binding.rvKebudayaan.layoutManager = GridLayoutManager(this, 2)
        binding.rvKebudayaan.setHasFixedSize(true)
        adapter.submitList(DataKebudayaan.kebudayaan)




    }
}