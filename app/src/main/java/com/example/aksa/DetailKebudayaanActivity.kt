package com.example.aksa

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.aksa.dataKebudayaan.Kebudayaan
import com.example.aksa.database.kebudayaan.CultureEntity
import com.example.aksa.databinding.ActivityDetailKebudayaanBinding
import com.google.android.material.card.MaterialCardView

class DetailKebudayaanActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailKebudayaanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailKebudayaanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val kebudayaan = intent.getParcelableExtra<Kebudayaan>("kebudayaan")
        binding.apply {
            if (kebudayaan != null) {
                imvKebudayaan.setImageResource(kebudayaan.img)
                tvJudul.text = kebudayaan.title
                desc1.text = kebudayaan.desc1
                desc2.text = kebudayaan.desc2
                desc3.text = kebudayaan.desc3
                desc4.text = kebudayaan.desc4
                desc5.text = kebudayaan.desc5
            }
        }


    }
}