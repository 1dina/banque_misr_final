package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.source.remote.models.account.AccountCreationRequest
import com.example.speedotransfer.data.source.remote.models.account.AccountCreationResponse
import com.example.speedotransfer.domain.repository.DashboardRepository

interface CreateAccountUseCase {
    suspend fun execute (account : AccountCreationRequest) : Result<AccountCreationResponse>
}

class CreateAccountUseCaseImp (val dashboardRepository: DashboardRepository) : CreateAccountUseCase{
    override suspend fun execute(account: AccountCreationRequest): Result<AccountCreationResponse> {
        val response = dashboardRepository.createAccount(account)
        return if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else { Result.failure(Exception(response.errorBody()?.string())) }
    }
}