package com.example.speedotransfer.data.models

import androidx.annotation.DrawableRes

data class RecentTransactionItem (@DrawableRes val image : Int,
    val personName : String , val cardDetails : String, val date : String , val status : String,
    val price : String)