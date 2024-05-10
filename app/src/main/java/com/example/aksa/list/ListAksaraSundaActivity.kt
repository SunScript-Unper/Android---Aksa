package com.example.aksa.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aksa.NgagalenaActivity
import com.example.aksa.R
import com.example.aksa.SwaraActivity
import com.example.aksa.databinding.ActivityListAksaraSundaBinding

class ListAksaraSundaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListAksaraSundaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListAksaraSundaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.apply {
            buttonSwara.setOnClickListener {
                val intent = Intent(this@ListAksaraSundaActivity, SwaraActivity::class.java)
                startActivity(intent)
                finish()
            }
            buttonNgagalena.setOnClickListener {
                val intent = Intent(this@ListAksaraSundaActivity, NgagalenaActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}