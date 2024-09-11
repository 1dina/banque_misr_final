package com.example.speedotransfer.data.models

import com.google.gson.annotations.SerializedName

data class FavouriteAddition(
    var accountId: Int,
    var name: String,
    var userId: Int = 0
)