package com.example.speedotransfer.repo

import android.util.Log
import com.auth0.jwt.JWT
import com.example.speedotransfer.data.models.AuthData
import com.example.speedotransfer.data.models.UserAuthRegisterRequest
import com.example.speedotransfer.data.models.UserAuthRegisterResponse
import com.example.speedotransfer.data.models.UserLoginRequest
import com.example.speedotransfer.data.models.UserLoginResponse
import com.example.speedotransfer.data.source.BankingAPICallable
import com.example.speedotransfer.domain.repository.AuthRepository
import retrofit2.Response

class Repository(val apiService: BankingAPICallable) : AuthRepository {
    override suspend fun createUserAuth(user: UserAuthRegisterRequest): Response<UserAuthRegisterResponse> {
        return apiService.createUser(user)
    }

    override suspend fun loginUserAuth(user: UserLoginRequest): AuthData {
        val response = apiService.logInUser(user)
        val token = response.body()?.accessToken
        val decodedJWT = JWT.decode(token)
        Log.e("trace","user Id is ${response.body()?.accessToken}")
        val userId = decodedJWT.getClaim("id").asInt()
        Log.e("trace","user Id is $userId")
        return AuthData(userId)
    }
}