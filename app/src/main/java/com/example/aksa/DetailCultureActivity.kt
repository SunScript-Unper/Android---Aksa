package com.example.aksa

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.aksa.database.kebudayaan.CultureEntity
import com.example.aksa.databinding.ActivityDetailCultureBinding
import com.example.aksa.databinding.ContentScrollingBinding

class DetailCultureActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailCultureBinding
    private lateinit var contentScroll : ContentScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCultureBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val culture = intent.getParcelableExtra<CultureEntity>("kebudayaan")


        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = null

        if (culture != null) {
            loadBackdropImage(culture.image)
//            loadImage(culture.image)
            contentScroll = ContentScrollingBinding.bind(binding.root.findViewById(R.id.content_scroll))
            contentScroll.tvJudulKebudayaan.text = culture.title
            Glide.with(this)
                .load(culture.image)
                .into(binding.imageDetailKebudayaan)


        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("DetailCultureActivity", "onDestroy() called")

    }

//    private fun loadImage(imageUrl: String) {
//        Glide.with(this)
//            .asBitmap()
//            .load(imageUrl)
//            .centerCrop()
//            .into(object : CustomTarget<Bitmap>() {
//                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
//
//                    binding.appBar.background = BitmapDrawable(resources, resource)
//                }
//
//                override fun onLoadCleared(placeholder: Drawable?) {
//
//                }
//
//
//            })
//    }

    private fun loadBackdropImage(imageUrl: String) {
        Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .centerCrop()
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    // Set the loaded bitmap as contentScrim for CollapsingToolbarLayout
                    binding.toolbarLayout.contentScrim = BitmapDrawable(resources, resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // Not needed in this case
                }
            })

    }
}
