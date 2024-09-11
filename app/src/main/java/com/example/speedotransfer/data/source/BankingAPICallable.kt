package com.example.speedotransfer.data.source

import com.example.speedotransfer.Constants
import com.example.speedotransfer.data.models.UserInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


interface BankingAPICallable {
    
    @GET(Constants.INFO_ENDPOINT)
    suspend fun getInfo(@Header("Authorization") accessToken: String): Response<UserInfoResponse>

}