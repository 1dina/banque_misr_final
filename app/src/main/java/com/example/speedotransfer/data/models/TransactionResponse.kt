package com.example.speedotransfer.data.models

data class TransactionRequest(
    var senderAccountNum: Int = 0,
    val reciverAccountNum: Int,
    val amount: Int
)

data class TransactionResponse(
    val amount: Int,
    val reciverAccountNum: Int,
    val senderAccountNum: Int,
    val status: String,
    val trascationTime: String
)