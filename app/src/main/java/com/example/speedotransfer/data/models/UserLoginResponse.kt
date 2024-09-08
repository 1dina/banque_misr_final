package com.example.speedotransfer.data.models

data class UserLoginResponse(
    val accessToken: String,
    val refreshToken: String
)
data class UserLoginRequest(
    val email: String = "",
    val password: String = ""
)
