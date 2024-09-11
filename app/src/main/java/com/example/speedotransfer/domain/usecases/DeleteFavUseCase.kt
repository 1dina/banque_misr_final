package com.example.speedotransfer.domain.usecases

import com.example.speedotransfer.domain.repository.DashboardRepository

interface DeleteFavUseCase {
    suspend fun execute (accountId : Int) : Result<String>
}

class DeleteFavUseCaseImpl(val repo : DashboardRepository) : DeleteFavUseCase{
    override suspend fun execute(accountId: Int): Result<String> {
        return try{
            val response = repo.deleteFromFav(accountId)
            if (response.isSuccessful)
                Result.success("Item has been deleted")
            else
                Result.failure(Exception("There is no account with that Id"))
        }catch (e:Exception) {
            Result.failure(e)
        }
    }

}