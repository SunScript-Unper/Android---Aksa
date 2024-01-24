package com.example.aksa

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.aksa.databinding.ActivitySplashScreenBinding
import com.example.aksa.pref.ThemePreferences
import com.example.aksa.pref.ThemeViewModel
import com.example.aksa.pref.ThemeViewModelFactory
import com.example.aksa.pref.dataStore

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var themeViewModel : ThemeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = ThemePreferences.getInstance(this@SplashScreenActivity.dataStore)
        themeViewModel = ViewModelProvider(this, ThemeViewModelFactory(pref))[ThemeViewModel::class.java]

        themeViewModel.getThemeSetting().observe(this) { isDarkModeActive : Boolean ->
            AppCompatDelegate.setDefaultNightMode(
                if (isDarkModeActive) {
                    AppCompatDelegate.MODE_NIGHT_YES
                } else {
                    AppCompatDelegate.MODE_NIGHT_NO
                }
            )
        }

        Handler().postDelayed({
            val intent = android.content.Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}