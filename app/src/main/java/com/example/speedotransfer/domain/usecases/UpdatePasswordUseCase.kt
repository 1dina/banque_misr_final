package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.models.transaction.history.Passwords
import com.example.speedotransfer.domain.repository.DashboardRepository

interface UpdatePasswordUseCase{
    suspend fun execute(passwords: Passwords) : Result<String>
}


class UpdatePasswordUseCaseImpl (val dashboardRepository: DashboardRepository) : UpdatePasswordUseCase{
    override suspend fun execute(passwords: Passwords): Result<String> {
        return try {
            val response = dashboardRepository.updatePassword(passwords)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}