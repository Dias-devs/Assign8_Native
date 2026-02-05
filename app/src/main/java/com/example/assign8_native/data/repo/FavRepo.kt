package com.example.assign8_native.data.repo

import com.example.assign8_native.auth.FirebaseAuthManager
import com.example.assign8_native.data.model.FavoriteCity
import com.google.firebase.database.*

class FirebaseFavoritesRepository {

    private val db = FirebaseDatabase.getInstance()
    private val uid = FirebaseAuthManager.getUid()!!

    private val ref = db.getReference("favorites").child(uid)

    fun observeFavorites(
        onChange: (List<FavoriteCity>) -> Unit
    ) {
        ref.addValueEventListener(object : ValueEventListener {
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
        val id = ref.push().key!!
        val fav = FavoriteCity(
            id = id,
            city = city,
            createdBy = uid,
            note = note
        )
        ref.child(id).setValue(fav)
    }

    fun update(fav: FavoriteCity) {
        ref.child(fav.id).setValue(fav)
    }

    fun delete(id: String) {
        ref.child(id).removeValue()
    }
}