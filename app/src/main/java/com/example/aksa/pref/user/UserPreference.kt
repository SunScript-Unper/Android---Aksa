package com.example.aksa.pref.user

import android.content.Context
import android.content.SharedPreferences

class UserPreference(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    companion object {
        private const val ID = "id"
        private const val TOKEN = "token"
        private const val NAME = "name"
        private const val EMAIL = "email"
        private const val PREF_NAME = "MyPreferences"
        private var instance: UserPreference? = null

        fun getInstance(context: Context): UserPreference {
            if (instance == null) {
                instance = UserPreference(context.applicationContext)
            }
            return instance as UserPreference
        }

    }

    fun saveLoginSession(id: Int, name: String, email: String, token: String) {
        editor.putInt(ID, id)
        editor.putString(NAME, name)
        editor.putString(EMAIL, email)
        editor.putString(TOKEN, token)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.contains(TOKEN)
    }

    fun getUserId(): Int {
        return sharedPreferences.getInt(ID, -1)
    }

    fun getName(): String {
        return sharedPreferences.getString(NAME, "")!!
    }

    fun getUserToken(): String {
        return sharedPreferences.getString(TOKEN, "")!!
    }

    fun getUserEmail(): String {
        return sharedPreferences.getString(EMAIL, "")!!
    }

    fun logout() {
        editor.clear()
        editor.apply()
    }


}