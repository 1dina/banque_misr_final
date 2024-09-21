package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.models.account.AccountCreationRequest
import com.example.speedotransfer.data.models.account.AccountCreationResponse
import com.example.speedotransfer.domain.repository.DashboardRepository

interface CreateAccountUseCase {
    suspend fun execute (account : AccountCreationRequest) : Result<AccountCreationResponse>
}

class CreateAccountUseCaseImp (val dashboardRepository: DashboardRepository) : CreateAccountUseCase{
    override suspend fun execute(account: AccountCreationRequest): Result<AccountCreationResponse> {
        return try{
            val response = dashboardRepository.createAccount(account)
            if (response.isSuccessful)
                Result.success(response.body()!!)
            else
                Result.failure(Exception(response.errorBody()?.string()))
        }catch (e : Exception) {
            Result.failure(e)
        }
    }
}