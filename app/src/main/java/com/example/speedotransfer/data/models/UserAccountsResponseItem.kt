package com.example.speedotransfer.data.models


data class UserAccountsResponseItem(
    val balance: Int,
    val created_at: String,
    val id: Int,
    val type: String,
    val userId: Int
)