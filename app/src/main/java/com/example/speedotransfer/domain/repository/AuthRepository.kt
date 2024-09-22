package com.example.speedotransfer.domain.repository

import com.example.speedotransfer.data.source.remote.models.AuthData
import com.example.speedotransfer.data.source.remote.models.user.UserAuthRegisterRequest
import com.example.speedotransfer.data.source.remote.models.user.UserAuthRegisterResponse
import com.example.speedotransfer.data.source.remote.models.user.UserLoginRequest
import retrofit2.Response


interface AuthRepository  {
    suspend fun createUserAuth(user : UserAuthRegisterRequest) : Response<UserAuthRegisterResponse>
    suspend fun loginUserAuth (user : UserLoginRequest) : AuthData
    suspend fun saveToken(accessToken:String,refreshToken :String)
    suspend fun fetchAccessToken():String?
    suspend fun fetchRefreshToken():String?
    suspend fun signOutUser() :Response<String>
}