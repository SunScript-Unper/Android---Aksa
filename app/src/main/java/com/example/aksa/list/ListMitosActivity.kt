package com.example.aksa.list

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aksa.R
import com.example.aksa.adapter.MitosAdapter
import com.example.aksa.dataMitos.DataMitos
import com.example.aksa.databinding.ActivityListMitosBinding

class ListMitosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListMitosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListMitosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val adapter = MitosAdapter()
        binding.rvMitos.adapter = adapter
        binding.rvMitos.setHasFixedSize(true)
        adapter.submitList(DataMitos.listMitos)
    }
}