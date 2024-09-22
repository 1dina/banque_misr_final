package com.example.speedotransfer.data.source.remote.models.user

data class UserAuthRegisterRequest(
    val name: String = "",
    val email: String ="",
    val password: String="",
    val country: String="",
    val dateofbirth: String=""
)

data class UserAuthRegisterResponse(
    val id: Int,
    val name: String,
    val email: String,
    val country: String
)
