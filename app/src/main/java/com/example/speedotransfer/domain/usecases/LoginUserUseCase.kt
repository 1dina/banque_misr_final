package com.example.speedotransfer.domain.usecases

import android.util.Log
import com.example.speedotransfer.data.models.AuthData
import com.example.speedotransfer.data.models.UserLoginRequest
import com.example.speedotransfer.data.models.UserLoginResponse
import com.example.speedotransfer.domain.repository.AuthRepository

interface LoginUserUseCase{
    suspend fun execute(user : UserLoginRequest) : Result<AuthData>
}

class LoginUserUseCaseImpl (private val authRepository: AuthRepository) : LoginUserUseCase{
    override suspend fun execute(user: UserLoginRequest):Result<AuthData> {
        return try {
            val authData = authRepository.loginUserAuth(user)
            Result.success(authData)
        } catch (e: Exception) {
            Result.failure(e)
        }
        }
    }
