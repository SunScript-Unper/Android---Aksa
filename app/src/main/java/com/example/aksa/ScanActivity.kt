package com.example.aksa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aksa.databinding.ActivityScanBinding
import io.alterac.blurkit.BlurKit

class ScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        BlurKit.init(this)
    }
}