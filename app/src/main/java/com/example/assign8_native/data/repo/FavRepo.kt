package com.example.assign8_native.data.repo

import com.example.assign8_native.data.model.FavoriteCity
import com.google.firebase.database.*

class FirebaseFavoritesRepository(private val uid: String) {
    private val dbRef = FirebaseDatabase
        .getInstance(
            "https://weather-project-766b7-default-rtdb.asia-southeast1.firebasedatabase.app"
        )
        .getReference("favorites")
        .child(uid)
    fun observeFavorites(onChange: (List<FavoriteCity>) -> Unit) {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull {
                    it.getValue(FavoriteCity::class.java)
                }
                onChange(list)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun add(city: String, note: String) {
        val id = dbRef.push().key!!
        val fav = FavoriteCity(
            id = id,
            city = city,
            createdBy = uid,
            note = note
        )
        dbRef.child(id).setValue(fav)
    }

    fun update(fav: FavoriteCity) {
        dbRef.child(fav.id).setValue(fav)
    }

    fun delete(id: String) {
        dbRef.child(id).removeValue()
    }
}