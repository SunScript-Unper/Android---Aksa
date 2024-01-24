package com.example.aksa

import android.content.Intent
import android.graphics.fonts.FontStyle
import android.graphics.fonts.FontVariationAxis
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.View
import androidx.annotation.RequiresApi
import com.example.aksa.databinding.ActivityRegisterBinding
import com.google.android.material.resources.TextAppearance
import java.time.format.TextStyle

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        spanCustom()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun spanCustom(){
        val loginText = binding.textHaveaccount

        val span = SpannableString(getString(R.string.login))
        val styleSpan = StyleSpan(FontStyle.FONT_WEIGHT_BOLD)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        span.setSpan(clickableSpan, 0, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(styleSpan, 0, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        loginText.text = TextUtils.concat("Do you have an account? ", span)
        loginText.movementMethod = LinkMovementMethod.getInstance()
    }
}