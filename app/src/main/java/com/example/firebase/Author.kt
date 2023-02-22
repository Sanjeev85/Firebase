package com.example.firebase

import com.google.firebase.database.Exclude

data class User(
    val userId: String? = null,
    val username: String? = null,
    val email: String? = null
)
