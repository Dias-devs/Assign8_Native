package com.example.assign8.util

sealed class NetworkResult<out T> {
    object Loading : NetworkResult<Nothing>()
    data class Success<T>(val data: T, val offline: Boolean = false) : NetworkResult<T>()
    data class Error(val message: String) : NetworkResult<Nothing>()
}