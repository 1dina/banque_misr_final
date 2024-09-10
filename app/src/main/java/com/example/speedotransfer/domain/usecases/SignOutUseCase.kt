package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.domain.repository.AuthRepository

interface SignOutUseCase {
    suspend fun execute(): Result<String>
}

class SignOutUseCaseImp(private val authRepository: AuthRepository) : SignOutUseCase {
    override suspend fun execute(): Result<String> {
        return try {
            val response = authRepository.signOutUser()
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Unknown Error"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}