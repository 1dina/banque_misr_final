package com.example.speedotransfer.repo

import com.example.speedotransfer.data.models.UserAuthRegisterRequest
import com.example.speedotransfer.data.models.UserAuthRegisterResponse
import com.example.speedotransfer.data.source.BankingAPICallable
import com.example.speedotransfer.domain.repository.AuthRepository
import retrofit2.Response

class Repository(val apiService: BankingAPICallable) : AuthRepository {
    override suspend fun createUserAuth(user: UserAuthRegisterRequest): Response<UserAuthRegisterResponse> {
        return apiService.createUser(user)
    }
}