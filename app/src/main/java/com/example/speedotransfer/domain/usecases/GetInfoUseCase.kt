package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.source.remote.models.user.info.UserInfoResponse
import com.example.speedotransfer.domain.repository.DashboardRepository

interface GetInfoUseCase {
    suspend fun execute(): Result<UserInfoResponse>
}

class GetInfoUseCaseImpl(private val dashboardRepository: DashboardRepository) : GetInfoUseCase {
    override suspend fun execute(): Result<UserInfoResponse> {
        val response = dashboardRepository.fetchInfo()
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Exception("Error: ${response.message()}"))
        }
    }
}