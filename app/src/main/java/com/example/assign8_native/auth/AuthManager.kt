package com.example.assign8_native.auth

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthManager {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signInAnonymously(onSuccess: (String) -> Unit) {
        auth.signInAnonymously()
            .addOnSuccessListener {
                onSuccess(auth.currentUser!!.uid)
            }
    }

    fun getUid(): String? = auth.currentUser?.uid
}