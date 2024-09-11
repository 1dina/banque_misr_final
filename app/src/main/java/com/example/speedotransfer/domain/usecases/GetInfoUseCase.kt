package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.models.UserInfoResponse
import com.example.speedotransfer.domain.repository.ProfileRepository

interface GetInfoUseCase{
    suspend fun execute() : Result<UserInfoResponse>
}

class GetInfoUseCaseImpl (val profileRepository: ProfileRepository) : GetInfoUseCase{
    override suspend fun execute(): Result<UserInfoResponse> {
        return try {
            val response = profileRepository.getInfo()
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