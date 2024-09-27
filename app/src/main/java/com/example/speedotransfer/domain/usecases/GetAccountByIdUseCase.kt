package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.source.remote.models.account.AccountByIdResponse
import com.example.speedotransfer.domain.repository.DashboardRepository

interface GetAccountByIdUseCase {
    suspend fun execute(accountId: String): Result<AccountByIdResponse>

}

class GetAccountByIdUseCaseImpl(private val dashboardRepository: DashboardRepository) :
    GetAccountByIdUseCase {
    override suspend fun execute(accountId: String): Result<AccountByIdResponse> {
        val response = dashboardRepository.fetchUserAccountById(accountId)
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            val message = "there is no account with this if"
            throw Exception(message)
        }
    }
}