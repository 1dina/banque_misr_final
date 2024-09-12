package com.example.speedotransfer.data.models

data class Content(
    val amount: Int,
    val createdTimeStamp: String,
    val id: Int,
    val receiverName: String,
    val reciverAccountId: Int,
    val senderAccountId: Int,
    val senderName: String
)