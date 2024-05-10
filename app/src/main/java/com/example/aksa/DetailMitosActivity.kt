package com.example.aksa

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aksa.dataMitos.Mitos
import com.example.aksa.databinding.ActivityDetailMitosBinding

class DetailMitosActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailMitosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailMitosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mitos = intent.getParcelableExtra<Mitos>("mitos")
        binding.apply {
            if (mitos != null) {
                imvMitos.setImageResource(mitos.img)
                tvJudulMitos.text = mitos.name
                descMitos.text = mitos.desc
            }
        }
    }
}