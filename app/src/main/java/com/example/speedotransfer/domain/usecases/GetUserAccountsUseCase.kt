package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.data.models.userAccResponse.UserAccountsResponseItem
import com.example.speedotransfer.domain.repository.DashboardRepository

interface GetUserAccountsUseCase {
    suspend fun execute() : Result<ArrayList<UserAccountsResponseItem>>
}
class GetUserAccountsUseCaseImpl (val repo : DashboardRepository) : GetUserAccountsUseCase{
    override suspend fun execute(): Result<ArrayList<UserAccountsResponseItem>> {
        return try{
            val response = repo.getUserAccounts()
            if (response.isSuccessful)
                Result.success(response.body()!!)
            else
                Result.failure(Exception(response.errorBody()?.string()))
        }catch (e : Exception) {
            Result.failure(e)
        }
    }

}