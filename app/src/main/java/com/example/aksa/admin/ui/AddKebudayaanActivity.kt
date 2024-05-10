package com.example.aksa.admin.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toFile
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.aksa.R
import com.example.aksa.database.kebudayaan.CultureEntity
import com.example.aksa.database.kebudayaan.KebudayaanEntity
import com.example.aksa.database.kebudayaan.KebudayaanViewModel
import com.example.aksa.database.kebudayaan.KebudyaanFactory
import com.example.aksa.databinding.ActivityAddKebudayaanBinding
import com.example.aksa.utils.uriToFile

class AddKebudayaanActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAddKebudayaanBinding
    private lateinit var kebudayaanViewModel: KebudayaanViewModel

    private var currentImageUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddKebudayaanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        kebudayaanViewModel = ViewModelProvider(this, KebudyaanFactory.getInstance(this))[KebudayaanViewModel::class.java]

        binding.btGallery.setOnClickListener {
            startGallery()
        }

        binding.btnAddKebudayaan.setOnClickListener {
            val title = binding.edtJudul.text.toString()
            val desc = binding.edtDeskripsi.text.toString()

            currentImageUri?.let {
                val imageFile = uriToFile(it, this)
                val kebudayaanEntity = CultureEntity(title = title, desc = desc, image = imageFile.path)
                Log.d("KEBUDAYAAN", kebudayaanEntity.toString())
                kebudayaanViewModel.insertKebudayaan(kebudayaanEntity)
            }

        }

        kebudayaanViewModel.insert.observe(this){ response ->
            if (response != null){
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Success")
                dialog.setMessage("Menambahkan Kebudayaan Berhasil")
                dialog.setPositiveButton("Ok") { _, _ ->
                    binding.edtJudul.text?.clear()
                    binding.edtDeskripsi.text?.clear()
                    binding.imvAddImageKebudayaan.setImageResource(R.drawable.aksa)

                }
                dialog.show()

            } else {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Failed")
                dialog.setMessage("Menambahkan Kebudayaan Gagal")
                dialog.setPositiveButton("Ok") { _, _ ->

                }
                dialog.show()
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

    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.imvAddImageKebudayaan.setImageURI(it)
        }
    }
}