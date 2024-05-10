package com.example.aksa.database.kebudayaan

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData

class KebudayaanRepository(private val kebudayaanDao: KebudayaanDao) {
    companion object{
        const val PAGE_SIZE = 30
        const val PLACEHOLDER = true

        @Volatile
        private var INSTANCE : KebudayaanRepository? = null

        fun getInstance(context: Context) : KebudayaanRepository {
            return INSTANCE ?: synchronized(this){
                if (INSTANCE == null) {
                    val database = CultureDatabase.getInstance(context)
                    INSTANCE = KebudayaanRepository(database.cultureDao())
                }
                return INSTANCE as KebudayaanRepository
            }

        }
    }

    fun getAllKebudayaan(): LiveData<PagingData<CultureEntity>> {

        val pagingConfig = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = PLACEHOLDER
        )
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                kebudayaanDao.getAllKebudayaan()
            }
        ).liveData

    }

    suspend fun insertKebudayaan(kebudayaanEntity: CultureEntity){
        kebudayaanDao.insertKebudayaan(kebudayaanEntity)
    }

    suspend fun deleteKebudayaan(id: Long){
        kebudayaanDao.deleteKebudayaan(id)
    }
}