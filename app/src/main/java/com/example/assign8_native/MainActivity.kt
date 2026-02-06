package com.example.assign8_native

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.example.assign8_native.ui.WeatherScreen
import com.example.assign8_native.ui.FavoritesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                var showFavorites by remember { mutableStateOf(false) }

                Column {
                    Button(onClick = { showFavorites = !showFavorites }) {
                        Text(if (showFavorites) "Show Weather" else "Show Favorites")
                    }

                    if (showFavorites) {
                        FavoritesScreen()
                    } else {
                        WeatherScreen()
                    }
                }
            }
        }
    }
}