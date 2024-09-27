package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.source.remote.models.transaction.history.Passwords
import com.example.speedotransfer.domain.repository.DashboardRepository

interface UpdatePasswordUseCase {
    suspend fun execute(passwords: Passwords): Result<String>
}


class UpdatePasswordUseCaseImpl(private val dashboardRepository: DashboardRepository) :
    UpdatePasswordUseCase {
    override suspend fun execute(passwords: Passwords): Result<String> {
        val response = dashboardRepository.updatePassword(passwords)
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Exception("Error: ${response.message()}"))
        }
    }
}