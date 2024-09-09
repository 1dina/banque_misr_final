package com.example.speedotransfer.data.source

import com.example.speedotransfer.Constants
import com.example.speedotransfer.data.models.UserAuthRegisterRequest
import com.example.speedotransfer.data.models.UserAuthRegisterResponse
import com.example.speedotransfer.data.models.UserLoginRequest
import com.example.speedotransfer.data.models.UserLoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BankingAPICallable {
    @POST(Constants.SIGNUP_ENDPOINT)
    suspend fun createUser(@Body userAuthRegisterRequest: UserAuthRegisterRequest): Response<UserAuthRegisterResponse>

    @POST(Constants.SIGNIN_ENDPOINT)
    suspend fun logInUser (@Body loginRequest: UserLoginRequest) : Response<UserLoginResponse>

}