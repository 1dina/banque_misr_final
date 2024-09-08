package com.example.speedotransfer.domain.repository

import com.example.speedotransfer.data.models.AuthData
import com.example.speedotransfer.data.models.UserAuthRegisterRequest
import com.example.speedotransfer.data.models.UserAuthRegisterResponse
import com.example.speedotransfer.data.models.UserLoginRequest
import com.example.speedotransfer.data.models.UserLoginResponse
import retrofit2.Response


interface AuthRepository  {
    suspend fun createUserAuth(user : UserAuthRegisterRequest) : Response<UserAuthRegisterResponse>
    suspend fun loginUserAuth (user : UserLoginRequest) : AuthData
}