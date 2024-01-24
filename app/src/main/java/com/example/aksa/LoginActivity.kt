package com.example.aksa

import android.content.Intent
import android.graphics.fonts.FontStyle
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.view.View
import androidx.annotation.RequiresApi
import com.example.aksa.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spanCustom()

        binding.btnSignin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun spanCustom(){
        val registerText = binding.textHavenotaccount

        val span = SpannableString(getString(R.string.register))
        val styleSpan = StyleSpan(FontStyle.FONT_WEIGHT_BOLD)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        span.setSpan(clickableSpan, 0, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        span.setSpan(styleSpan, 0, span.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        registerText.text = TextUtils.concat("Don't have an account? ", span)
        registerText.movementMethod = LinkMovementMethod.getInstance()
    }
}