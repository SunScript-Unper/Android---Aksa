package com.example.aksa

import android.animation.ObjectAnimator
import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import com.example.aksa.databinding.ActivityScanBinding
import com.example.aksa.ml.Model
import com.example.aksa.utils.createCustomTempFile
import com.example.aksa.utils.getImageUri
import com.example.aksa.utils.uriToFile
//import com.example.aksa.utils.reduceFileImage
import com.google.android.material.card.MaterialCardView
import eightbitlab.com.blurview.RenderEffectBlur
import eightbitlab.com.blurview.RenderScriptBlur
import io.alterac.blurkit.BlurKit
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

class ScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanBinding
    private lateinit var tvImage: ImageView
    private lateinit var btnCamera : MaterialCardView
    private lateinit var btnGallery : MaterialCardView
    private lateinit var btnProcess : MaterialCardView

    private var currentImageUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animation()


        tvImage = binding.imvImageprocess
        btnCamera = binding.btnCapture
        btnGallery = binding.btnAddpicture
        btnProcess = binding.btnProcess


        btnCamera.setOnClickListener {
            startCamera()
        }

        btnGallery.setOnClickListener {
            startGallery()
        }

        btnProcess.setOnClickListener {
            imageProcess()
        }

    }


    private fun startCamera(){
        currentImageUri = getImageUri(this)
        launchCamera.launch(currentImageUri)
    }

    private val launchCamera = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ){ isSucces ->
        if (isSucces){
            showImage()
        }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            tvImage.setImageURI(it)
        }
    }

    private fun imageProcess(){
        val model = Model.newInstance(this)
        val imageUri = currentImageUri ?: return
        val inputImage = BitmapFactory.decodeStream(contentResolver.openInputStream(imageUri))

        val resizedImage = Bitmap.createScaledBitmap(inputImage, 224, 224, true)

        val byteBuffer = convertBitmapToByteBuffer(resizedImage)
// Creates inputs for reference.
        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

        Log.d("Image URI", "showImage: $inputImage")

// Runs model inference and gets result.
        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

//        tangkap index
        val maxIndex = outputFeature0.floatArray.max()

//        tangkap nama class dari index nya
        val className = getClassNameFromIndex(maxIndex!!.toInt())



        Log.d("Output", className)

        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Hasil")
        dialog.setMessage(className)
        dialog.setPositiveButton("OK") { dialog, _ ->

        }
        val alert = dialog.create()
        alert.show()


// Releases model resources if no longer used.
        model.close()

    }

    private fun getClassNameFromIndex(index: Int): String {
        val classNames = arrayOf("Aksara A", "Aksara Ka", "Aksara Sa", "Aksara ba")
        return classNames[index]
    }

    fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(4 * 224 * 224 * 3)
            .order(ByteOrder.nativeOrder())
        bitmap.getPixels(IntArray(224 * 224), 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        for (pixel in 0 until 224 * 224) {
            val value = bitmap.getPixel(pixel % 224, pixel / 224)
            byteBuffer.putFloat(((value shr 16 and 0xFF) - 127.5f) / 127.5f)
            byteBuffer.putFloat(((value shr 8 and 0xFF) - 127.5f) / 127.5f)
            byteBuffer.putFloat(((value and 0xFF) - 127.5f) / 127.5f)
        }
        return byteBuffer
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