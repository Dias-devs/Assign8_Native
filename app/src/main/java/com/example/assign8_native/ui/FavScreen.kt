@file:Suppress("UNCHECKED_CAST")

package com.example.assign8_native.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assign8_native.data.model.FavoriteCity
import com.example.assign8_native.viewmodel.FavoritesViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun FavoritesScreen(modifier: Modifier = Modifier) {
    val auth = FirebaseAuth.getInstance()
    var uid by remember { mutableStateOf(auth.currentUser?.uid) }

    LaunchedEffect(Unit) {
        if (uid == null) {
            auth.signInAnonymously()
                .addOnSuccessListener {
                    uid = it.user?.uid
                }
        }
    }

    if (uid == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    val viewModel: FavoritesViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FavoritesViewModel(uid!!) as T
            }
        }
    )

    val favorites = viewModel.favorites.value

    var cityInput by remember { mutableStateOf("") }
    var noteInput by remember { mutableStateOf("") }
    var editingId by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = cityInput,
            onValueChange = { cityInput = it },
            label = { Text("City") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = noteInput,
            onValueChange = { noteInput = it },
            label = { Text("Note") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (cityInput.isNotBlank()) {
                    if (editingId != null) {
                        viewModel.update(
                            FavoriteCity(
                                id = editingId!!,
                                city = cityInput,
                                note = noteInput,
                                createdBy = uid!!
                            )
                        )
                        editingId = null
                    } else {
                        viewModel.add(cityInput, noteInput)
                    }
                    cityInput = ""
                    noteInput = ""
                }
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(if (editingId != null) "Update Favorite" else "Add Favorite")
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

        LazyColumn {
            items(favorites) { fav ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {

                        Text(text = fav.city, style = MaterialTheme.typography.titleMedium)
                        Text(text = fav.note)

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(
                                onClick = {
                                    cityInput = fav.city
                                    noteInput = fav.note
                                    editingId = fav.id
                                }
                            ) {
                                Text("Edit")
                            }

                            TextButton(
                                onClick = { viewModel.delete(fav.id) }
                            ) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }
    }
}