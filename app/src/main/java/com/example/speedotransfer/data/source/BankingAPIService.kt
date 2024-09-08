package com.example.speedotransfer.data.source

import com.example.speedotransfer.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BankingAPIService {
    private val retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Lazy properties: the value is computed "only" on first access
    val callable: BankingAPICallable by lazy {
        retrofit.create(BankingAPICallable::class.java)
    }
}