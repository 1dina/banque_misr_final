package com.example.speedotransfer.domain.repository

import com.example.speedotransfer.data.models.UserAuthRegisterRequest
import com.example.speedotransfer.data.models.UserAuthRegisterResponse
import retrofit2.Response


interface AuthRepository  {
    suspend fun createUserAuth(user : UserAuthRegisterRequest) : Response<UserAuthRegisterResponse>
}