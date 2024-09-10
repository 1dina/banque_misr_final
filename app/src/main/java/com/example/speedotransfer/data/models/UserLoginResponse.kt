package com.example.speedotransfer.data.models

data class UserLoginResponse(
    val refreshToken: String,
    val accessToken: String
)
data class UserLoginRequest(
    val email: String = "",
    val password: String = ""
)
