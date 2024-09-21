package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.models.userInfoResponse.UserInfoResponse
import com.example.speedotransfer.domain.repository.DashboardRepository

interface GetInfoUseCase{
    suspend fun execute() : Result<UserInfoResponse>
}

class GetInfoUseCaseImpl (val dashboardRepository: DashboardRepository) : GetInfoUseCase{
    override suspend fun execute(): Result<UserInfoResponse> {
        return try {
            val response = dashboardRepository.getInfo()
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