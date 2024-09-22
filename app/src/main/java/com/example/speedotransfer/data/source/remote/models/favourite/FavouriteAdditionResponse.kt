package com.example.speedotransfer.data.source.remote.models.favourite


data class FavouriteAdditionResponse(
    var accountId: Int,
    var name: String,
    var userId: Int = 0
)