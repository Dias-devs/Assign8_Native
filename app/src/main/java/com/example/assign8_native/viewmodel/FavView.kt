package com.example.assign8_native.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.assign8_native.data.model.FavoriteCity
import com.example.assign8_native.data.repo.FirebaseFavoritesRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(uid: String) : ViewModel() {
    private val repo = FirebaseFavoritesRepository(uid)

    private val _favorites = mutableStateOf<List<FavoriteCity>>(emptyList())
    val favorites: State<List<FavoriteCity>> = _favorites

    init {
        repo.observeFavorites { list ->
            viewModelScope.launch {
                _favorites.value = list
            }
        }
    }

    fun add(city: String, note: String) {
        repo.add(city, note)
    }

    fun delete(id: String) {
        repo.delete(id)
    }
}