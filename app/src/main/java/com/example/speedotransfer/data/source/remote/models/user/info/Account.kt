package com.example.speedotransfer.data.source.remote.models.user.info

data class Account(
    val balance: Int,
    val created_at: String,
    val id: Int,
    val type: String,
    val userId: Int
)