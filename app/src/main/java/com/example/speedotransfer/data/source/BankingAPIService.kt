package com.example.speedotransfer.data.source

import com.example.speedotransfer.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BankingAPIService {
    private val gson = GsonBuilder()
        .setLenient()
        .create()
    private val retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val callable: BankingAPICallable by lazy {
        retrofit.create(BankingAPICallable::class.java)
    }
}