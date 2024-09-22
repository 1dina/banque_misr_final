package com.example.speedotransfer.data.source.remote.models.account

import com.google.gson.annotations.SerializedName

data class AccountCreationRequest(val type : String ="" , val balance : Int =0)
data class AccountCreationResponse (
    @SerializedName("accountId") val id : Int,
    val type :String,
    val balance : Int ,
    val created_at : String)

