package com.example.speedotransfer.data.models

data class UserInfoResponse(
    val accounts: List<Account>,
    val country: String,
    val dateofbirth: String,
    val email: String,
    val id: Int,
    val name: String
)