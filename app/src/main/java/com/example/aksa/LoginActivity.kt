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
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.aksa.databinding.ActivityLoginBinding
import com.example.aksa.model.LoginViewModel
import com.example.aksa.model.ViewModelFactory
import com.example.aksa.pref.user.UserPreference
import com.example.aksa.pref.user.dataStore
import com.example.aksa.response.LoginRequest

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var userPreference: UserPreference

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(LoginViewModel::class.java)
        userPreference = UserPreference.getInstance(this.dataStore)

        spanCustom()

        binding.btnSignin.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            loginViewModel.loading.observe(this) { isLoading ->
                binding.loadingLogin!!.visibility = if (isLoading) View.VISIBLE else View.GONE
            }

            loginViewModel.login(LoginRequest(email, password))
            loginViewModel.login.observe(this) {response ->
                if(response != null) {
                    val id = response.loginResult?.id
                    val emailuser = response.loginResult?.email
                    val name = response.loginResult?.name
                    val token = response.loginResult?.token

                    if (id != null && emailuser != null && name != null && token != null) {

                    }
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
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