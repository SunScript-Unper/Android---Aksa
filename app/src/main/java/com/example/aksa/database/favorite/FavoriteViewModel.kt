package com.example.aksa.database.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import kotlinx.coroutines.launch

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    private val _favorite = MutableLiveData<Boolean>()
    val favorite: LiveData<Boolean> = _favorite

    private val _deleteAll = MutableLiveData<Boolean>()
    val deleteAll: LiveData<Boolean> = _deleteAll

    fun getAllFavorite(): LiveData<PagingData<FavoriteEntity>> {
        return favoriteRepository.getAllFavorite()
    }

    fun insertHuruf(favoriteEntity: FavoriteEntity) {
        viewModelScope.launch {
            try {
                favoriteRepository.insertHuruf(favoriteEntity)
                _favorite.value = true
            } catch (e: Exception) {
                _favorite.value = false
            }
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            try {
                favoriteRepository.deleteAll()
                _deleteAll.value = true
            } catch (e: Exception) {
                _deleteAll.value = false
            }
        }
    }
}