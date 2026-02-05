package com.example.assign8_native.data.model

data class FavoriteCity(
    val id: String = "",
    val city: String = "",
    val note: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val createdBy: String = ""
)