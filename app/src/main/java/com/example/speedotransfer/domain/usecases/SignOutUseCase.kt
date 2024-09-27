package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.domain.repository.AuthRepository

interface SignOutUseCase {
    suspend fun execute(): Result<String>
}

class SignOutUseCaseImp(private val authRepository: AuthRepository) : SignOutUseCase {
    override suspend fun execute(): Result<String> {
        val response = authRepository.signOutUser()
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Exception("Unknown Error"))
        }
    }

}