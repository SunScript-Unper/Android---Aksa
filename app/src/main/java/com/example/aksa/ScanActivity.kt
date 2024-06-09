package com.example.aksa

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.aksa.database.favorite.FavoriteEntity
import com.example.aksa.database.favorite.FavoriteFactory
import com.example.aksa.database.favorite.FavoriteViewModel
import com.example.aksa.databinding.ActivityScanBinding
import com.example.aksa.ml.Model
import com.example.aksa.model.PredictionFactory
import com.example.aksa.model.PredictionViewModel
import com.example.aksa.utils.getImageUri
import com.example.aksa.utils.reduceFileImage
import com.example.aksa.utils.uriToFile
//import com.example.aksa.utils.reduceFileImage
import com.google.android.material.card.MaterialCardView
import eightbitlab.com.blurview.RenderScriptBlur
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

class ScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanBinding
    private lateinit var tvImage: ImageView
    private lateinit var btnGallery : MaterialCardView
    private lateinit var btnProcess : MaterialCardView
    private lateinit var btnCamera : MaterialCardView
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var predictionViewModel: PredictionViewModel

    private var currentImageUri: Uri? = null



    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animation()
        favoriteViewModel = ViewModelProvider(this, FavoriteFactory.getInstance(this))[FavoriteViewModel::class.java]
        predictionViewModel = ViewModelProvider(this, PredictionFactory.getInstance(this))[PredictionViewModel::class.java]

        tvImage = binding.imvImageprocess
        btnGallery = binding.btnAddpicture
        btnProcess = binding.btnProcess
        btnCamera = binding.btnCamera

        btnCamera.setOnClickListener {
            openCamera()
        }

        btnGallery.setOnClickListener {
            startGallery()
        }

        btnProcess.setOnClickListener {
            if (currentImageUri == null) {
                val alertDialog = AlertDialog.Builder(this)
                    .setTitle("Gambar kosong")
                    .setMessage("Silahkan pilih gambar terlebih dahulu")
                    .setPositiveButton("OK") { _, _ ->

                    }
                    val build = alertDialog.create()
                    build.show()
            } else {
                prediction()
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun prediction(){

        predictionViewModel.isLoading.observe(this){loading ->
            binding.loadingScan.visibility = if (loading) View.VISIBLE else View.GONE
        }
        currentImageUri?.let {
            val gambar = uriToFile(it, this)
            val reduceFile = reduceFileImage(gambar)

            val requestImageFile = gambar.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "file",
                reduceFile.name,
                requestImageFile
            )

            predictionViewModel.predict(imageMultipart)
        }

        predictionViewModel.prediction.observe(this) { prediction ->
            val result = prediction.prediction.toString()
            if (prediction != null) {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Hasil")
                dialog.setMessage("Aksara $result, Apakah ingin ditambahkan ke favorite?")
                dialog.setPositiveButton("OK") {_,_ ->
                    currentImageUri?.let {
                        val imageFile = uriToFile(it, this)
                        favoriteViewModel.insertHuruf(FavoriteEntity(huruf = result, image = imageFile.path))
                    }

                    favoriteViewModel.favorite.observe(this){
                        if(it == true){
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                dialog.setNegativeButton("Tidak") {_,_ ->

                }

                val alert = dialog.create()
                alert.show()
            }
        }

    }

    private fun startGallery() {
        pickImageLauncher.launch(
            PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly
            )
        )
    }

    private fun openCamera() {
        currentImageUri = getImageUri(this)
        launchCamera.launch(currentImageUri)
    }

    private val launchCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        if (isSuccess) {
            showImage()
        }
    }


    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            // Gambar telah dipilih, lakukan sesuatu dengan URI gambar
            // Contoh: menampilkan gambar ke ImageView
            currentImageUri = uri
            showImage()
        }
    }
    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            tvImage.setImageURI(it)
            binding.tvImageBox.setImageURI(it)
            binding.tvImageBox2.setImageURI(it)
            binding.tvImageBox3.setImageURI(it)
        }
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