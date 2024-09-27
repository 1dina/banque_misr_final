package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.source.remote.models.AuthData
import com.example.speedotransfer.data.source.remote.models.user.UserLoginRequest
import com.example.speedotransfer.domain.repository.AuthRepository

interface LoginUserUseCase {
    suspend fun execute(user: UserLoginRequest): Result<AuthData>
}

class LoginUserUseCaseImpl(private val authRepository: AuthRepository) : LoginUserUseCase {
    override suspend fun execute(user: UserLoginRequest): Result<AuthData> {
        val authData = authRepository.loginUserAuth(user)
        return Result.success(authData)

    }
}
