package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.models.AuthData
import com.example.speedotransfer.data.models.UserLoginRequest
import com.example.speedotransfer.data.models.UserLoginResponse
import com.example.speedotransfer.domain.repository.AuthRepository

interface LoginUserUseCase{
    suspend fun execute(user : UserLoginRequest) : AuthData
}

class LoginUserUseCaseImpl (val authRepository: AuthRepository) : LoginUserUseCase{
    override suspend fun execute(user: UserLoginRequest): AuthData {
        return authRepository.loginUserAuth(user)
    }
    }
