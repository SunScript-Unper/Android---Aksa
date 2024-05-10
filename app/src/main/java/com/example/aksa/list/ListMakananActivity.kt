package com.example.aksa.list

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aksa.R
import com.example.aksa.adapter.MakananAdapter
import com.example.aksa.dataMakanan.DataMakanan
import com.example.aksa.databinding.ActivityListMakananBinding

class ListMakananActivity : AppCompatActivity() {

    private lateinit var binding : ActivityListMakananBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListMakananBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = MakananAdapter()
        binding.rvMakanan.adapter = adapter
        binding.rvMakanan.setHasFixedSize(true)
        adapter.submitList(DataMakanan.listMakanan)
    }
}