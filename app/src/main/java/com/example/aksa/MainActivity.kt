package com.example.aksa

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aksa.databinding.ActivityMainBinding
import com.example.aksa.pref.ThemePreferences
import com.example.aksa.pref.ThemeViewModel
import com.example.aksa.pref.ThemeViewModelFactory
import com.example.aksa.pref.dataStore
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var themeViewModel : ThemeViewModel


    private val iconMap = mapOf(
        R.id.home to Pair(R.drawable.fluent_home_outline, R.drawable.fluent_home_filled),
        R.id.favorite to Pair(R.drawable.iconoir_heart_outline, R.drawable.iconoir_heart_fill),
        R.id.profile to Pair(R.drawable.fluent_person_outline, R.drawable.fluent_person_filled),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigationView
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        val initialItemId = R.id.home  // Set item ID to R.id.home for initial Fluent Home Filled icon
        updateIcon(initialItemId)

        navView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.navigation_home)
                    updateIcon(menuItem.itemId)
                    true
                }
                R.id.favorite -> {
                    navController.navigate(R.id.navigation_favorite)
                    updateIcon(menuItem.itemId)
                    true
                }
                R.id.profile -> {
                    navController.navigate(R.id.navigation_profile)
                    updateIcon(menuItem.itemId)
                    true
                }
                else -> false
            }
        }


        val pref = ThemePreferences.getInstance(this.dataStore)
        themeViewModel = ViewModelProvider(this, ThemeViewModelFactory(pref))[ThemeViewModel::class.java]
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.action_theme -> {
                    toggleNightMode()
                    true
                }
                else -> false
            }
        }

        themeViewModel.getThemeSetting().observe(this) { isNightMode ->
            val newNightMode = if (isNightMode) {
                AppCompatDelegate.MODE_NIGHT_YES // Aktifkan mode gelap
            } else {
                AppCompatDelegate.MODE_NIGHT_NO // Matikan mode gelap
            }

            AppCompatDelegate.setDefaultNightMode(newNightMode)

            // Set ikon menu berdasarkan tema yang aktif
            val iconId = if (isNightMode) {
                R.drawable.ph_sun
            } else {
                R.drawable.ph_moon
            }
            binding.toolbar.menu.findItem(R.id.action_theme)?.icon =
                ContextCompat.getDrawable(this, iconId)
        }

    }

    private fun toggleNightMode() {
        val isNightMode = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES

        lifecycleScope.launch {
            themeViewModel.saveThemeSetting(!isNightMode)
        }

        // Set tema berdasarkan preferensi yang diperbarui
        val newNightMode = if (!isNightMode) {
            AppCompatDelegate.MODE_NIGHT_YES // Aktifkan mode gelap
        } else {
            AppCompatDelegate.MODE_NIGHT_NO // Matikan mode gelap
        }

        AppCompatDelegate.setDefaultNightMode(newNightMode)

        val iconResId = if (newNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            R.drawable.ph_moon
        } else {
            R.drawable.ph_sun
        }

        // Set ikon menu
        binding.toolbar.menu.findItem(R.id.action_theme)?.icon =
            ContextCompat.getDrawable(this, iconResId)


    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun updateIcon(iconId: Int) {
        iconMap.forEach { (id, pair) ->
            val menuItem = binding.bottomNavigationView.menu.findItem(id)
            if (id == iconId) {
                menuItem.icon = getDrawable(pair.second)
            } else {
                menuItem.icon = getDrawable(pair.first)
            }
        }
    }
}