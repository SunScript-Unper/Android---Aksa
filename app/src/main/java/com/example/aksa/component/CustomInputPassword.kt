package com.example.aksa.component

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

class CustomInputPassword: TextInputEditText {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init(){
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val password = s.toString()
                error = if (password.length < 8) {
                    "Password tidak boleh kurang dari 8 karakter"
                } else if (!password.matches(Regex("^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)\$"))) {
                    "Password harus terdiri dari huruf dan angka"
                } else {
                    null
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
}