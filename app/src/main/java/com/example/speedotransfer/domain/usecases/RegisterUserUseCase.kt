package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.source.remote.models.user.UserAuthRegisterRequest
import com.example.speedotransfer.data.source.remote.models.user.UserAuthRegisterResponse
import com.example.speedotransfer.domain.repository.AuthRepository

interface RegisterUserUseCase {
    suspend fun execute(user: UserAuthRegisterRequest): Result<UserAuthRegisterResponse>
}

class RegisterUserUseCaseImpl(private val authRepository: AuthRepository) : RegisterUserUseCase {
    override suspend fun execute(user: UserAuthRegisterRequest): Result<UserAuthRegisterResponse> {
        val response = authRepository.createUserAuth(user)
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            val errorMessage = response.errorBody()?.string() ?: "Unknown error"
            Result.failure(Exception(errorMessage))
        }
    }
}