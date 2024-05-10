package com.example.aksa.list

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.aksa.R
import com.example.aksa.adapter.PupuhAdapter
import com.example.aksa.dataPupuh.DataPupuh
import com.example.aksa.databinding.ActivityListPupuhBinding

class ListPupuhActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListPupuhBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListPupuhBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = PupuhAdapter()
        binding.rvPupuh.adapter = adapter
        binding.rvPupuh.setHasFixedSize(true)
        adapter.submitList(DataPupuh.lisPupuh)
    }
}