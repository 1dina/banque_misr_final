package com.example.speedotransfer.data.models.account

data class AccountByIdResponse(
    val balance: Int,
    val created_at: String,
    val id: Int,
    val type: String,
    val userId: Int
)