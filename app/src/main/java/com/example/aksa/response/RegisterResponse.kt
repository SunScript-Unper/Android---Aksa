package com.example.aksa.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class RegisterRequest(
	@SerializedName("name")
	val name: String,

	@SerializedName("email")
	val email: String?,

	@SerializedName("password")
	val password: String,
)
