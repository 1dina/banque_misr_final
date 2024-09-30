package com.example.speedotransfer.data.source.remote.retrofit

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.speedotransfer.data.constants.Constants
import com.example.speedotransfer.data.source.local.SecureStorageDataSource
import com.example.speedotransfer.data.source.remote.AuthInterceptor
import com.example.speedotransfer.ui.CustomApplication
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitInstance {
    private val gson = GsonBuilder()
        .setLenient()
        .create()
    private val secureStorage = SecureStorageDataSource(CustomApplication.getAppContext())

    private val client = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor(CustomApplication.getAppContext()))
        .addInterceptor(AuthInterceptor(secureStorage, CustomApplication.getAppContext()))
        .build()

    private val retrofit = Retrofit
        .Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    val callable: BankingAPICallable by lazy {
        retrofit.create(BankingAPICallable::class.java)
    }

}