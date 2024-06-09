package com.example.aksa.pref.user

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")
class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveName(user: User) {
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = user.name
        }
    }

    suspend fun saveNamaKuis(namaKuis: NamaKuis){
        dataStore.edit { preferences ->
            preferences[NAMA_KUIS] = namaKuis.namaKuis
        }
    }


    suspend fun deleteNamaKuis(){
        dataStore.edit { preferences ->
            preferences[NAMA_KUIS] = ""
        }
    }

    suspend fun getNamaKuis(): String? {
        val preferences = dataStore.data.first()
        return preferences[NAMA_KUIS]
    }

    suspend fun isUserLoggedIn(): Boolean {
        val preferences = dataStore.data.first()
        val userId = preferences[USERNAME_KEY]
        return !userId.isNullOrEmpty()
    }

    suspend fun getName(): String? {
        val preferences = dataStore.data.first()
        return preferences[USERNAME_KEY]
    }




    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null
        private val USERNAME_KEY = stringPreferencesKey("name")
        private val NAMA_KUIS = stringPreferencesKey("kuis")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}