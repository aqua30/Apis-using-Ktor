package com.aqua30.domain

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val first_name: String,
    val last_name: String,
    val age: Int,
    val dob: String,
    val city: String,
)
