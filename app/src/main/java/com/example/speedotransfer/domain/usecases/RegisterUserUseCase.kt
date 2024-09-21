package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.models.user.UserAuthRegisterRequest
import com.example.speedotransfer.data.models.user.UserAuthRegisterResponse
import com.example.speedotransfer.domain.repository.AuthRepository

interface RegisterUserUseCase {
    suspend fun execute(user: UserAuthRegisterRequest) :Result<UserAuthRegisterResponse>
}

class RegisterUserUseCaseImpl(private val authRepository : AuthRepository) : RegisterUserUseCase{
    override suspend fun execute(user: UserAuthRegisterRequest): Result<UserAuthRegisterResponse> {
        return try {
            val response = authRepository.createUserAuth(user)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}