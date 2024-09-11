package com.example.speedotransfer.data.models.dummy

import androidx.annotation.DrawableRes

data class TransactionCard (
    val userName: String,
    val visaType: String,
    val date: String,
    val  state: String,
    val  isSuccess: Boolean,
    val  amountOfMoney: Int)