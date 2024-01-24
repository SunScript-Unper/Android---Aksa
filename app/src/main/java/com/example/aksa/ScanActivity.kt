package com.example.aksa

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.example.aksa.databinding.ActivityScanBinding
import eightbitlab.com.blurview.RenderEffectBlur
import eightbitlab.com.blurview.RenderScriptBlur
import io.alterac.blurkit.BlurKit

class ScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animation()

    }

    private fun animation () {
        val cardView = binding.materialCardView2
        val animation = ObjectAnimator.ofFloat(cardView, "translationY", 1200f, 0f)
        animation.duration = 2200
        animation.interpolator = android.view.animation.AccelerateDecelerateInterpolator()
        animation.start()
        val decorView = window.decorView
        val rootView = decorView.findViewById<ViewGroup>(android.R.id.content)
        val radius = 20f
        val blurView = binding.blurView

        blurView.setupWith(rootView, RenderScriptBlur(this))
            .setBlurRadius(radius)
            .setBlurEnabled(true)
    }
}